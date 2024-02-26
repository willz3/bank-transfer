package com.example.banktransfer.core.infrastructure.protocols;

public interface IToResponseMapper <T, U> {
    U toResponse(T entity);
}
