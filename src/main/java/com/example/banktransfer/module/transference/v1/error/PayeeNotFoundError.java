package com.example.banktransfer.module.transference.v1.error;

public class PayeeNotFoundError extends Error {
    public PayeeNotFoundError() {
        super("Payee not found.");
    }
}
