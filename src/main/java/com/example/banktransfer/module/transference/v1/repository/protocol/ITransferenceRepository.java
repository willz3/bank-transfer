package com.example.banktransfer.module.transference.v1.repository.protocol;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public interface ITransferenceRepository {
    TransferenceEntity create(TransferenceEntity entity);
}
