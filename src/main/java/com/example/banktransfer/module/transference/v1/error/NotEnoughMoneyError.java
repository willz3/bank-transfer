package com.example.banktransfer.module.transference.v1.error;

public class NotEnoughMoneyError extends Error {
    public NotEnoughMoneyError() {
        super("There is not enough money to make the transference.");
    }
}
