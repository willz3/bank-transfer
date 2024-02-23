package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.error.MerchantPayerError;
import com.example.banktransfer.module.transference.v1.error.NotEnoughMoneyError;
import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import com.example.banktransfer.module.transference.v1.error.PayeeNotFoundError;
import com.example.banktransfer.module.transference.v1.error.PayerNotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class CreateTransferenceUseCase implements ICreateTransferenceUseCase {

    private final IUserGateway userGateway;

    private final ITransferenceGateway transferenceGateway;

    public CreateTransferenceUseCase(IUserGateway userGateway, ITransferenceGateway transferenceGateway) {
        this.userGateway = userGateway;
        this.transferenceGateway = transferenceGateway;
    }

    @Override
    @Transactional
    public Either<Error, TransferenceEntity> execute(TransferenceEntity debitTransference) {
        Optional<UserEntity> payerOptional = userGateway.findUserById(debitTransference.getPayerId());
        Optional<UserEntity> payeeOptional = userGateway.findUserById(debitTransference.getPayeeId());

        if (payerOptional.isEmpty()) {
            return Either.Left(new PayerNotFoundError());
        }

        if (payeeOptional.isEmpty()) {
            return Either.Left(new PayeeNotFoundError());
        }

        if (payerOptional.get().getType() == UserEntity.UserType.MERCHANT) {
            return Either.Left(new MerchantPayerError());
        }

        if (!debitTransference.validateDebit(payerOptional.get())) {
            return Either.Left(new NotEnoughMoneyError());
        }

        TransferenceEntity creditTransference = debitTransference.toCredit();
        UserEntity payerToUpdate = payerOptional.get().toDebit(debitTransference.getAmount());
        UserEntity payeeToUpdate = payeeOptional.get().toCredit(debitTransference.getAmount());

        TransferenceEntity transferenceToReturn = transferenceGateway.create(debitTransference);

        transferenceGateway.create(creditTransference);
        userGateway.updateUser(payerToUpdate);
        userGateway.updateUser(payeeToUpdate);

        return Either.Right(transferenceToReturn);
    }
}
