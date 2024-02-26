package com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper;


import com.example.banktransfer.core.infrastructure.protocols.IToEntityMapper;
import com.example.banktransfer.core.infrastructure.protocols.IToResponseMapper;
import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceRequestDTO;
import com.example.banktransfer.module.transference.v1.dto.response.CreateTransferenceResponseDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;


public interface ICreateTransferenceMapper extends IToEntityMapper<CreateTransferenceRequestDTO, TransferenceEntity>, IToResponseMapper<TransferenceEntity, CreateTransferenceResponseDTO> {

}
