package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.error.MerchantPayerError;
import com.example.banktransfer.module.transference.v1.error.NotEnoughMoneyError;
import com.example.banktransfer.module.shared.gateway.user.IUserGateway;


public class CreateTransferenceUseCase implements ICreateTransferenceUseCase {

    private final IUserGateway userGateway;

    private final ITransferenceGateway transferenceGateway;

    public CreateTransferenceUseCase(IUserGateway userGateway, ITransferenceGateway transferenceGateway) {
        this.userGateway = userGateway;
        this.transferenceGateway = transferenceGateway;
    }

    @Override
    public Either<Error, TransferenceEntity> execute(TransferenceEntity debitTransference) {
        UserEntity payer = userGateway.findUserById(debitTransference.getPayerId());
        UserEntity payee = userGateway.findUserById(debitTransference.getPayeeId());

        if (payer.getType() == UserEntity.UserType.MERCHANT) {
            return Either.Left(new MerchantPayerError());
        }

        if (!debitTransference.validateDebit(payer)) {
            return Either.Left(new NotEnoughMoneyError());
        }

        TransferenceEntity creditTransference = debitTransference.toCredit();
        UserEntity payerToUpdate = payer.toDebit(debitTransference.getValue());
        UserEntity payeeToUpdate = payee.toCredit(debitTransference.getValue());

        TransferenceEntity transferenceToReturn = transferenceGateway.create(debitTransference);
        transferenceGateway.create(creditTransference);
        userGateway.updateUser(payerToUpdate);
        userGateway.updateUser(payeeToUpdate);


        return Either.Right(transferenceToReturn);
    }
}
