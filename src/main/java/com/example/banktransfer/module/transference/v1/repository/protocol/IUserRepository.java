package com.example.banktransfer.module.transference.v1.repository.protocol;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;

public interface IUserRepository {
    UserEntity findByUserId(Long userId);
    UserEntity updateUser(UserEntity entity);
}
