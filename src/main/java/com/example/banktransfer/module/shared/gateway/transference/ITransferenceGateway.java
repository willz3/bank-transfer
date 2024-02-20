package com.example.banktransfer.module.shared.gateway.transference;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public interface ITransferenceGateway {
    TransferenceEntity create(TransferenceEntity entity);
}
