package com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public class CreateTransferenceMapper implements ICreateTransferenceMapper {
    @Override
    public TransferenceEntity toEntity(CreateTransferenceDTO dto) {
        return new TransferenceEntity(dto.value(), dto.payer(), dto.payee());
    }
}
