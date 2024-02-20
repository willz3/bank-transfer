package com.example.banktransfer.main.config;

import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import com.example.banktransfer.module.shared.gateway.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    IUserGateway userGateway() {return new UserGateway(); }
}
