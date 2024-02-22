package com.example.banktransfer.module.transference.v1.controller;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.error.PayerNotFoundError;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.usecase.CreateTransference.ICreateTransferenceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;


class TransferenceControllerTest {

    private TransferenceEntity ENTITY_MOCK = makeEntity();

    private CreateTransferenceDTO DTO = makeDTO();

    @Mock
    private ICreateTransferenceMapper mapper;

    @Mock
    private ICreateTransferenceUseCase useCase;

    @InjectMocks
    private TransferenceController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(useCase.execute(ENTITY_MOCK)).thenReturn(Either.Right(ENTITY_MOCK));
        when(mapper.toEntity(DTO)).thenReturn(ENTITY_MOCK);
    }

    @Test
    @DisplayName("should call mapper with correct values")
    void callMapperWithCorrectValues() {
        sut.handle(DTO);

        verify(mapper, times(1)).toEntity(DTO);
    }

    @Test
    @DisplayName("should call use case with correct values")
    void callUseCaseWithCorrectValues() {
        CreateTransferenceDTO dto = makeDTO();
        when(mapper.toEntity(dto)).thenReturn(ENTITY_MOCK);

        sut.handle(dto);

        verify(useCase, times(1)).execute(ENTITY_MOCK);
    }

    @Test
    @DisplayName("should return 400 if use case execution return PayerNotFoundError.")
    void returnBadRequest() {
        CreateTransferenceDTO dto = makeDTO();

        PayerNotFoundError error = new PayerNotFoundError();
        when(useCase.execute(ENTITY_MOCK)).thenReturn(Either.Left(error));

        ResponseEntity<Object> result = sut.handle(dto);

        assertEquals(result.getStatusCode().value(), 400);
        assertEquals(result.getBody(), error.getMessage());
    }

    @Test
    @DisplayName("should return 201 if use case execution succeeds.")
    void returnCrated() {
        CreateTransferenceDTO dto = makeDTO();

        ResponseEntity<Object> result = sut.handle(dto);

        assertEquals(result.getStatusCode().value(), 201);
    }

    CreateTransferenceDTO makeDTO() {
        return new CreateTransferenceDTO(BigDecimal.valueOf(100), 1L, 2L);
    }

    TransferenceEntity makeEntity() {
        return new TransferenceEntity(BigDecimal.valueOf(100), 1L, 2L, TransferenceEntity.TransferenceType.DEBIT);
    }
}