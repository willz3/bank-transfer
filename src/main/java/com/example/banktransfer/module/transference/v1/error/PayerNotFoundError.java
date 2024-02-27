package com.example.banktransfer.module.transference.v1.error;

import com.example.banktransfer.core.domain.DomainError;

public class PayerNotFoundError extends DomainError {
    public PayerNotFoundError() {
        super("Payer not found.");
    }
}
