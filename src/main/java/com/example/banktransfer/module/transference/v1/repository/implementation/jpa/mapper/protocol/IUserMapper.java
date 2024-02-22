package com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol;

import com.example.banktransfer.core.infrastructure.protocols.IToEntityMapper;
import com.example.banktransfer.core.infrastructure.protocols.IToPersistenceMapper;
import com.example.banktransfer.infrastructure.db.jpa.model.User;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;

public interface IUserMapper extends IToEntityMapper<User, UserEntity>, IToPersistenceMapper<UserEntity, User> {
}
