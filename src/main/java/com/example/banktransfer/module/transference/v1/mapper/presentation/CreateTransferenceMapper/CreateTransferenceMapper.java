package com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceRequestDTO;
import com.example.banktransfer.module.transference.v1.dto.response.CreateTransferenceResponseDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public class CreateTransferenceMapper implements ICreateTransferenceMapper {

    @Override
    public TransferenceEntity toEntity(CreateTransferenceRequestDTO dto) {
        return new TransferenceEntity(dto.value(), dto.payer(), dto.payee(), TransferenceEntity.TransferenceType.DEBIT);
    }

    @Override
    public CreateTransferenceResponseDTO toResponse(TransferenceEntity entity) {
        return new CreateTransferenceResponseDTO(entity.getId(), entity.getAmount(), entity.getPayerId(), entity.getPayeeId(), entity.getCreatedAt());
    }
}
