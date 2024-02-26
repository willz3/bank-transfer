package com.example.banktransfer.module.transference.v1.controller;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceRequestDTO;
import com.example.banktransfer.module.transference.v1.dto.response.CreateTransferenceResponseDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.error.PayerNotFoundError;
import com.example.banktransfer.module.transference.v1.error.UnauthorizedTransactionError;
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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;


class TransferenceControllerTest {

    private TransferenceEntity ENTITY_MOCK = makeEntity();

    private CreateTransferenceRequestDTO REQUEST_DTO = makeRequestDTO();

    private CreateTransferenceResponseDTO RESPONSE_DTO = makeResponseDTO();

    private Date MOCK_DATE = new Date();

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
        when(mapper.toEntity(REQUEST_DTO)).thenReturn(ENTITY_MOCK);
        when(mapper.toResponse(ENTITY_MOCK)).thenReturn(RESPONSE_DTO);
    }

    @Test
    @DisplayName("should call mapper with correct values")
    void callMapperWithCorrectValues() {
        sut.handle(REQUEST_DTO);

        verify(mapper, times(1)).toEntity(REQUEST_DTO);
    }

    @Test
    @DisplayName("should call use case with correct values")
    void callUseCaseWithCorrectValues() {
        CreateTransferenceRequestDTO dto = makeRequestDTO();
        when(mapper.toEntity(dto)).thenReturn(ENTITY_MOCK);

        sut.handle(dto);

        verify(useCase, times(1)).execute(ENTITY_MOCK);
    }

    @Test
    @DisplayName("should return 400 if use case execution return PayerNotFoundError.")
    void returnBadRequest() {
        CreateTransferenceRequestDTO dto = makeRequestDTO();

        PayerNotFoundError error = new PayerNotFoundError();
        when(useCase.execute(ENTITY_MOCK)).thenReturn(Either.Left(error));

        ResponseEntity<Object> result = sut.handle(dto);

        assertEquals(result.getStatusCode().value(), 400);
        assertEquals(result.getBody(), error.getMessage());
    }

    @Test
    @DisplayName("should return 201 if use case execution succeeds.")
    void returnCrated() {
        CreateTransferenceRequestDTO dto = makeRequestDTO();

        ResponseEntity<Object> result = sut.handle(dto);

        assertEquals(result.getStatusCode().value(), 201);
        assertEquals(result.getBody(), RESPONSE_DTO);
    }

    @Test
    @DisplayName("should return 401 if use case return UnauthorizedTransactionError.")
    void returnUnauthorized() {
        CreateTransferenceRequestDTO dto = makeRequestDTO();
        UnauthorizedTransactionError error = new UnauthorizedTransactionError();
        when(useCase.execute(ENTITY_MOCK)).thenReturn(Either.Left(error));
        ResponseEntity<Object> result = sut.handle(dto);

        assertEquals(result.getStatusCode().value(), 401);
        assertEquals(result.getBody(), error.getMessage());
    }

    CreateTransferenceRequestDTO makeRequestDTO() {
        return new CreateTransferenceRequestDTO(BigDecimal.valueOf(100), 1L, 2L);
    }

    CreateTransferenceResponseDTO makeResponseDTO() {
        return new CreateTransferenceResponseDTO(1L, BigDecimal.valueOf(100), 1L, 2L, MOCK_DATE);
    }

    TransferenceEntity makeEntity() {
        return new TransferenceEntity(BigDecimal.valueOf(100), 1L, 2L, TransferenceEntity.TransferenceType.DEBIT);
    }
}