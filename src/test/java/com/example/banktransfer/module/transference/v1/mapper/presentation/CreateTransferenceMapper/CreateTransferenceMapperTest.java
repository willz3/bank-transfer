package com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceRequestDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransferenceMapperTest {
    @Test
    @DisplayName("should map from dto to entity with correct value")
    void toEntity() {
        CreateTransferenceMapper sut = new CreateTransferenceMapper();
        CreateTransferenceRequestDTO dto = makeDTO();
        TransferenceEntity entity = sut.toEntity(dto);

        assertEquals(entity.getPayeeId(), dto.payee());
        assertEquals(entity.getPayerId(), dto.payer());
        assertEquals(entity.getAmount(), dto.value());
    }

    CreateTransferenceRequestDTO makeDTO() {
        return new CreateTransferenceRequestDTO(BigDecimal.valueOf(100), 1L, 2L);
    }
}