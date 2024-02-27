package com.example.banktransfer.core.domain;

public abstract class DomainError extends Error {
    public DomainError(String message) {
        super(message);
    }
}
