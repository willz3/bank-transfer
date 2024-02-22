package com.example.banktransfer.module.shared.gateway.transference;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.repository.protocol.ITransferenceRepository;
import org.springframework.stereotype.Component;

@Component
public class TransferenceGateway implements ITransferenceGateway {

    private final ITransferenceRepository repository;

    public TransferenceGateway(ITransferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransferenceEntity create(TransferenceEntity entity) {
        return this.repository.create(entity);
    }
}
