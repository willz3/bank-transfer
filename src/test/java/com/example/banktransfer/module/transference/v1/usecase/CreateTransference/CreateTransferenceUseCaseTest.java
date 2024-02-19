package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.error.MerchantPayerError;
import com.example.banktransfer.module.transference.v1.gateway.user.IUserGateway;
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
    void callUserGatewayWithPayeeId() {
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

        Either<Error, Object> result = sut.execute(transferenceEntity);

        assertTrue(result.isLeft());
        assertInstanceOf(MerchantPayerError.class, result.getLeft());
    }

    @Test
    @DisplayName("should call transference validate with correct users")
    void shouldCallTransferenceValidateCorrectly() {
        TransferenceEntity transferenceEntityMock = mock(TransferenceEntity.class);
        when(transferenceEntityMock.getPayerId()).thenReturn(PAYER_ID);
        when(transferenceEntityMock.getPayeeId()).thenReturn(PAYEE_ID);
        sut.execute(transferenceEntityMock);

        verify(transferenceEntityMock, times(1)).validate(PAYER, PAYEE);
    }

    TransferenceEntity makeEntity() {
        return new TransferenceEntity(BigDecimal.valueOf(100), PAYER_ID, PAYEE_ID);
    }

    UserEntity makeUser(Long id, UserEntity.UserType userType, String document) {
        return new UserEntity(id, "any_name", document, "any_email@mail.com", "any_password", userType, BigDecimal.valueOf(100), null, null, null);
    }

}