package com.example.banktransfer.main.config;

import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.CreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.TransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.ITransferenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferenceConfig {

    @Bean
    ITransferenceMapper jpaTransferenceMapper() { return new TransferenceMapper(); }

    @Bean
    ICreateTransferenceMapper transferenceMapper() {
        return new CreateTransferenceMapper();
    }

}
