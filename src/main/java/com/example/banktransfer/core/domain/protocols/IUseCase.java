package com.example.banktransfer.core.domain.protocols;

import com.example.banktransfer.core.domain.DomainError;
import com.example.banktransfer.core.shared.logic.Either;

public interface IUseCase<T, U> {
    Either<DomainError, T> execute(U dto);
}
