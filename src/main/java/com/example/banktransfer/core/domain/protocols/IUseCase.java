package com.example.banktransfer.core.domain.protocols;

import com.example.banktransfer.core.shared.logic.Either;

public interface IUseCase<T, U> {
    Either<Error, T> execute(U dto);
}
