package com.example.banktransfer.module.transference.v1.error;

public class MerchantPayerError extends Error {
    public MerchantPayerError() {
        super("Merchant can't made a transference.");
    }
}
