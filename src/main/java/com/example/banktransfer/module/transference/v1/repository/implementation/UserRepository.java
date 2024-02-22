package com.example.banktransfer.module.transference.v1.repository.implementation;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.UserJpaRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.IUserMapper;
import com.example.banktransfer.module.transference.v1.repository.protocol.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepository implements IUserRepository {

    private UserJpaRepository jpaRepository;

    private final IUserMapper userMapper;

    public UserRepository(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserEntity> findByUserId(Long userId) {
        return jpaRepository.findById(userId).map(userMapper::toEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity entity) {
        return userMapper.toEntity(userMapper.toPersistence(entity));
    }
}
