package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

public interface ICreateTransferenceUseCase {
    Either<Error, TransferenceEntity> execute(TransferenceEntity transference);
}
