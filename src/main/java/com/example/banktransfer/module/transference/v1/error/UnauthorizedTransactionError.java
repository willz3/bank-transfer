package com.example.banktransfer.module.transference.v1.error;

import com.example.banktransfer.core.domain.DomainError;

public class UnauthorizedTransactionError extends DomainError {
    public UnauthorizedTransactionError() {
        super("Unauthorized transaction.");
    }
}
