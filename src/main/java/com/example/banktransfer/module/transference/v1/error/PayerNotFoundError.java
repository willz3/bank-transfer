package com.example.banktransfer.module.transference.v1.error;

public class PayerNotFoundError extends Error {
    public PayerNotFoundError() {
        super("Payer not found.");
    }
}
