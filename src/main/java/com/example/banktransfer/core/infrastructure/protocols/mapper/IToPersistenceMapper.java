package com.example.banktransfer.core.infrastructure.protocols.mapper;

public interface IToPersistenceMapper<T, U> {
    U toPersistence(T entity);
}
