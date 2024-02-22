package com.example.banktransfer.module.shared.gateway.user;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;

import java.util.Optional;

public interface IUserGateway {
    Optional<UserEntity> findUserById(Long id);
    UserEntity updateUser(UserEntity user);
}
