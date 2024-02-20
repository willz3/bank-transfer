package com.example.banktransfer.main.config;

import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.shared.gateway.transference.TransferenceGateway;
import com.example.banktransfer.module.shared.gateway.user.IUserGateway;
import com.example.banktransfer.module.shared.gateway.user.UserGateway;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.CreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferenceConfig {

    @Bean
    ICreateTransferenceMapper createTransferenceMapper() {
        return new CreateTransferenceMapper();
    }

}
