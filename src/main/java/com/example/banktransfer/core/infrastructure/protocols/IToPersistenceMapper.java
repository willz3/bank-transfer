package com.example.banktransfer.core.infrastructure.protocols;

public interface IToPersistenceMapper<T, U> {
    U toPersistence(T entity);
}
