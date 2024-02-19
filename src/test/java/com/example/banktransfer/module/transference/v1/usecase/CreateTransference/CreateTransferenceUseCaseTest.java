package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.gateway.user.IUserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class CreateTransferenceUseCaseTest {

    @Mock
    IUserGateway userGateway;

    @InjectMocks
    CreateTransferenceUseCase sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should call user gateway with correct values to find a payer.")
    void callUserGateway() {
        TransferenceEntity transferenceEntity = makeEntity();
        sut.execute(transferenceEntity);
        verify(userGateway, times(1)).findUserById(transferenceEntity.getPayerId());
    }

    TransferenceEntity makeEntity() {
        return new TransferenceEntity(BigDecimal.valueOf(100), 1L, 2L);
    }

}