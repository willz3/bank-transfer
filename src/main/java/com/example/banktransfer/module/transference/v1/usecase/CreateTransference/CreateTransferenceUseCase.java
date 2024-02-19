package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.gateway.user.IUserGateway;

public class CreateTransferenceUseCase implements ICreateTransferenceUseCase {

    private IUserGateway userGateway;

    public CreateTransferenceUseCase(IUserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Either<Error, Object> execute(TransferenceEntity transference) {
        UserEntity payer = userGateway.findUserById(transference.getPayerId());

        return Either.Right(null);
    }
}
