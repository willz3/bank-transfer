package com.example.banktransfer.module.transference.v1.gateway.user;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;

public interface IUserGateway {
    UserEntity findUserById(Long id);
}
