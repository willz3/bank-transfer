package com.example.banktransfer.module.transference.v1.controller;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceDTO;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class CreateTransferenceControllerTest {


    @Mock
    private ICreateTransferenceMapper mapper;

    @InjectMocks
    private CreateTransferenceController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should call mapper with correct values")
    void callMapperWithCorrectValues() {
        CreateTransferenceDTO dto = makeDTO();

        sut.handle(dto);

        verify(mapper, times(1)).toEntity(dto);
    }

    CreateTransferenceDTO makeDTO() {
        return new CreateTransferenceDTO(BigDecimal.valueOf(100), 1L, 2L);
    }

}