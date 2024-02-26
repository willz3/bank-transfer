package com.example.banktransfer.module.transference.v1.usecase.CreateTransference;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.shared.authorization.IAuthorization;
import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.error.*;
import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Service
public class CreateTransferenceUseCase implements ICreateTransferenceUseCase {

    private final IUserGateway userGateway;

    private final ITransferenceGateway transferenceGateway;

    private final IAuthorization authorizer;

    public CreateTransferenceUseCase(IUserGateway userGateway, ITransferenceGateway transferenceGateway, IAuthorization authorizer) {
        this.userGateway = userGateway;
        this.transferenceGateway = transferenceGateway;
        this.authorizer = authorizer;
    }

    @Override
    @Transactional
    public Either<Error, TransferenceEntity> execute(TransferenceEntity debitTransference) {

        CompletableFuture<Optional<UserEntity>> payerFuture = CompletableFuture.supplyAsync(()
                -> userGateway.findUserById(debitTransference.getPayerId()));

        CompletableFuture<Optional<UserEntity>> payeeFuture = CompletableFuture.supplyAsync(()
                -> userGateway.findUserById(debitTransference.getPayeeId()));

        CompletableFuture.allOf(payerFuture, payeeFuture).join();
        Optional<UserEntity> payerOptional = payerFuture.join();
        Optional<UserEntity> payeeOptional = payeeFuture.join();

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

        if(!authorizer.auth()) {
            return Either.Left(new UnauthorizedTransactionError());
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
