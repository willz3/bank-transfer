package com.example.banktransfer.core.infrastructure.protocols.mapper;

public interface IToResponseMapper <T, U> {
    U toResponse(T entity);
}
