package com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public interface ICreateTransferenceMapper {
     TransferenceEntity toEntity(CreateTransferenceDTO dto);
}
