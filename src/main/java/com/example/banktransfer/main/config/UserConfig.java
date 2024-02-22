package com.example.banktransfer.main.config;

import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import com.example.banktransfer.module.shared.gateway.user.UserGateway;
import com.example.banktransfer.module.transference.v1.repository.implementation.UserRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.UserMapper;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.IUserMapper;
import com.example.banktransfer.module.transference.v1.repository.protocol.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    IUserMapper userMapper() { return new UserMapper(); }
}
