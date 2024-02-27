package com.example.banktransfer.module.transference.v1.error;

import com.example.banktransfer.core.domain.DomainError;

public class NotEnoughMoneyError extends DomainError {
    public NotEnoughMoneyError() {
        super("There is not enough money to make the transference.");
    }
}
