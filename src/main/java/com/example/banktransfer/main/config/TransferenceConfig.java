package com.example.banktransfer.main.config;

import com.example.banktransfer.module.shared.gateway.transference.ITransferenceGateway;
import com.example.banktransfer.module.shared.gateway.transference.TransferenceGateway;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.CreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.implementation.TransferenceRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.TransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.ITransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.protocol.ITransferenceRepository;
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

    @Bean
    ITransferenceRepository transferenceRepository(ITransferenceMapper jpaTransferenceMapper) {
        return new TransferenceRepository(jpaTransferenceMapper);
    }

    @Bean
    ITransferenceGateway transferenceGateway(ITransferenceRepository transferenceRepository) { return new TransferenceGateway(transferenceRepository); }
}
