package com.example.banktransfer.module.transference.v1.repository.implementation;

import com.example.banktransfer.infrastructure.db.jpa.model.Transference;
import com.example.banktransfer.infrastructure.db.jpa.model.User;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.TransferenceJpaRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.UserJpaRepository;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.ITransferenceMapper;
import com.example.banktransfer.module.transference.v1.repository.protocol.ITransferenceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransferenceRepository implements ITransferenceRepository {

    private final UserJpaRepository jpaUserRepository;
    private final TransferenceJpaRepository repository;

    private final ITransferenceMapper mapper;

    public TransferenceRepository(UserJpaRepository jpaUserRepository, TransferenceJpaRepository repository, ITransferenceMapper mapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TransferenceEntity create(TransferenceEntity entity) {
        Optional<User> payer = jpaUserRepository.findById(entity.getPayerId());
        Optional<User> payee = jpaUserRepository.findById(entity.getPayeeId());
        Transference transference = mapper.toPersistence(entity);
        transference.setPayer(payer.get());
        transference.setPayee(payee.get());
        return mapper.toEntity(repository.save(transference));
    }
}
