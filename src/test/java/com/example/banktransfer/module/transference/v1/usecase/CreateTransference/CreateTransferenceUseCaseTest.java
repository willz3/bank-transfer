package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.error.MerchantPayerError;
import com.example.banktransfer.module.transference.v1.error.NotEnoughMoneyError;
import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class CreateTransferenceUseCaseTest {

    @Mock
    IUserGateway userGateway;

    @Mock
    ITransferenceGateway transferenceGateway;

    @InjectMocks
    CreateTransferenceUseCase sut;

    private final Long PAYER_ID = 1L;
    private final Long PAYEE_ID = 2L;
    private final String SSN = "46101743080";
    private final String ENI = "15964243000117";

    private final UserEntity PAYER = makeUser(PAYER_ID, UserEntity.UserType.COMMOM, SSN);
    private final UserEntity PAYEE = makeUser(PAYEE_ID, UserEntity.UserType.MERCHANT, ENI);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(userGateway.findUserById(PAYER_ID)).thenReturn(PAYER);
        when(userGateway.findUserById(PAYEE_ID)).thenReturn(PAYEE);
    }


    @Test
    @DisplayName("should call user gateway with correct values to find a payer.")
    void callUserGatewayPayerId() {
        TransferenceEntity transferenceEntity = makeEntity();
        sut.execute(transferenceEntity);
        verify(userGateway, times(1)).findUserById(PAYER_ID);
    }

    @Test
    @DisplayName("should call user gateway with correct values to find a payee.")
    void callUserGatewayPayeeId() {
        TransferenceEntity transferenceEntity = makeEntity();
        sut.execute(transferenceEntity);
        verify(userGateway, times(1)).findUserById(PAYEE_ID);
    }

    @Test
    @DisplayName("should return an error if the payer is a merchant")
    void shouldReturnErrorIfPayerIsMerchant() {
        TransferenceEntity transferenceEntity = makeEntity();
        UserEntity invalidPayer = makeUser(PAYER_ID, UserEntity.UserType.MERCHANT, ENI);

        when(userGateway.findUserById(transferenceEntity.getPayerId())).thenReturn(invalidPayer);

        Either<Error, TransferenceEntity> result = sut.execute(transferenceEntity);

        assertTrue(result.isLeft());
        assertInstanceOf(MerchantPayerError.class, result.getLeft());
    }

    @Test
    @DisplayName("should call transference validate with correct user")
    void shouldCallTransferenceValidateCorrectly() {
        TransferenceEntity transferenceEntityMock = mock(TransferenceEntity.class);
        when(transferenceEntityMock.getPayerId()).thenReturn(PAYER_ID);
        sut.execute(transferenceEntityMock);

        verify(transferenceEntityMock, times(1)).validateDebit(PAYER);
    }

    @Test
    @DisplayName("should return NotEnoughMoneyError if payer does not have enough money to make the transference.")
    void shouldReturnNotEnoughMoneyError() {
        TransferenceEntity transferenceEntityMock = mock(TransferenceEntity.class);
        when(transferenceEntityMock.getPayerId()).thenReturn(PAYER_ID);
        when(transferenceEntityMock.validateDebit(PAYER)).thenReturn(false);

        Either<Error, TransferenceEntity> result = sut.execute(transferenceEntityMock);

        assertTrue(result.isLeft());
        assertInstanceOf(NotEnoughMoneyError.class, result.getLeft());
    }

    @Test
    @DisplayName("should call toDebit of payer with correct value.")
    void shouldDebitWithCorrectValue() {
        TransferenceEntity transferenceEntity = makeEntity();
        UserEntity userEntityMock = mock(UserEntity.class);
        UserEntity userToReturn = makeUser(PAYER_ID, UserEntity.UserType.COMMOM, SSN);
        userToReturn.setBalance(BigDecimal.ZERO);

        when(userGateway.findUserById(transferenceEntity.getPayerId())).thenReturn(userEntityMock);
        when(userEntityMock.getBalance()).thenReturn(BigDecimal.valueOf(100));

        sut.execute(transferenceEntity);

        verify(userEntityMock, times(1)).toDebit(transferenceEntity.getValue());
    }

    @Test
    @DisplayName("should call toCredit of payee with correct value.")
    void shouldCreditWithCorrectValue() {
        TransferenceEntity transferenceEntity = makeEntity();
        UserEntity userEntityMock = mock(UserEntity.class);

        when(userGateway.findUserById(transferenceEntity.getPayeeId())).thenReturn(userEntityMock);
        when(userEntityMock.getBalance()).thenReturn(BigDecimal.valueOf(100));

        sut.execute(transferenceEntity);

        verify(userEntityMock, times(1)).toCredit(transferenceEntity.getValue());
    }

    @Test
    @DisplayName("should call transference gateway to create transfers.")
    void shouldCallTransferenceGateway() {
        TransferenceEntity transferenceDebitEntity = makeEntity();
        TransferenceEntity transferenceCreditEntity = makeEntity(PAYEE_ID, PAYER_ID);

        sut.execute(transferenceDebitEntity);

        verify(transferenceGateway, times(1)).create(argThat(obj -> obj.getPayerId().equals(transferenceCreditEntity.getPayerId()) && obj.getPayeeId().equals(transferenceCreditEntity.getPayeeId()) && obj.getValue().equals(transferenceCreditEntity.getValue())));
        verify(transferenceGateway, times(1)).create(argThat(obj -> obj.getPayerId().equals(transferenceDebitEntity.getPayerId()) && obj.getPayeeId().equals(transferenceDebitEntity.getPayeeId()) && obj.getValue().equals(transferenceDebitEntity.getValue())));
    }

    @Test
    @DisplayName("should call update user gateway with correct users.")
    void shouldCallUserGatewayUpdate() {
        TransferenceEntity transferenceDebitEntity = makeEntity();

        sut.execute(transferenceDebitEntity);

        verify(userGateway, times(1)).updateUser(argThat(obj -> obj.getId().equals(PAYER_ID) && obj.getBalance().equals(BigDecimal.ZERO) && obj.getType().equals(PAYER.getType()) && obj.getDocument().equals(PAYER.getDocument()) && obj.getEmail().equals(PAYER.getEmail())));
        verify(userGateway, times(1)).updateUser(argThat(obj -> obj.getId().equals(PAYEE_ID) && obj.getBalance().equals(BigDecimal.valueOf(200)) && obj.getType().equals(PAYEE.getType()) && obj.getDocument().equals(PAYEE.getDocument()) && obj.getEmail().equals(PAYEE.getEmail())));
    }

    TransferenceEntity makeEntity() {
        return new TransferenceEntity(BigDecimal.valueOf(100), PAYER_ID, PAYEE_ID, TransferenceEntity.TransferenceType.DEBIT);
    }

    TransferenceEntity makeEntity(Long payerId, Long payeeId) {
        return new TransferenceEntity(BigDecimal.valueOf(100), payerId, payeeId, TransferenceEntity.TransferenceType.DEBIT);
    }

    UserEntity makeUser(Long id, UserEntity.UserType userType, String document) {
        return new UserEntity(id, "any_name", document, "any_email@mail.com", "any_password", userType, BigDecimal.valueOf(100), null, null, null);
    }

}