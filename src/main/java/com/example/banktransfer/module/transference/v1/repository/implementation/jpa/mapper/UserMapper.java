package com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper;
import com.example.banktransfer.infrastructure.db.jpa.model.User;
import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.entity.builder.UserEntityBuilder;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.IUserMapper;

public class UserMapper implements IUserMapper {

    @Override
    public UserEntity toEntity(User raw) {
        return new UserEntityBuilder()//
                .withId(raw.getId())//
                .withType(raw.getType())//
                .withBalance(raw.getBalance())//
                .withEmail(raw.getEmail())//
                .withName(raw.getName())//
                .withDocument(raw.getDocument())//
                .withPassword(raw.getPassword())//
                .withCreatedAt(raw.getCreatedAt())//
                .withUpdatedAt(raw.getUpdatedAt())//
                .withDeletedAt(raw.getDeletedAt())
                .build();
    }

    @Override
    public User toPersistence(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getType(),
                entity.getBalance(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt());
    }
}
