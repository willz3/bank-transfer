package com.example.banktransfer.module.transference.v1.error;

import com.example.banktransfer.core.domain.DomainError;

public class MerchantPayerError extends DomainError {
    public MerchantPayerError() {
        super("Merchant can't made a transference.");
    }
}
