package com.example.banktransfer.module.transference.v1.repository.protocol;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByUserId(Long userId);
    UserEntity updateUser(UserEntity entity);
}
