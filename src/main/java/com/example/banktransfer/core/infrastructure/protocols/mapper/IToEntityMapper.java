package com.example.banktransfer.core.infrastructure.protocols.mapper;

import com.example.banktransfer.core.domain.Entity;

public interface IToEntityMapper<T, U> {
    U toEntity(T raw);
}
