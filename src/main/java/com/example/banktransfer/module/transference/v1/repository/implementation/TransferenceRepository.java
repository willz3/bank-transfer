package com.example.banktransfer.module.transference.v1.repository.implementation;

import com.example.banktransfer.infrastructure.db.jpa.model.Transference;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.TransferenceJpaRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.ITransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.protocol.ITransferenceRepository;
import org.springframework.stereotype.Component;

@Component
public class TransferenceRepository implements ITransferenceRepository {

    private TransferenceJpaRepository repository;

    private final ITransferenceMapper mapper;

    public TransferenceRepository(ITransferenceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TransferenceEntity create(TransferenceEntity entity) {
        Transference transference = repository.save(mapper.toPersistence(entity));
        return mapper.toEntity(transference);
    }
}
