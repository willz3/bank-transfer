package com.example.banktransfer.module.transference.v1.error;

public class UnauthorizedTransactionError extends Error {
    public UnauthorizedTransactionError() {
        super("Unauthorized transaction.");
    }
}
