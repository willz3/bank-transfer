package com.example.banktransfer.module.shared.gateway.user;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;
import com.example.banktransfer.module.transference.v1.repository.protocol.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGateway implements IUserGateway {

    private final IUserRepository repository;

    public UserGateway(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return this.repository.findByUserId(id);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return this.repository.updateUser(user);
    }
}
