package com.example.banktransfer.module.transference.v1.error;

import com.example.banktransfer.core.domain.DomainError;

public class PayeeNotFoundError extends DomainError {
    public PayeeNotFoundError() {
        super("Payee not found.");
    }
}
