package com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper;

import com.example.banktransfer.infrastructure.db.jpa.model.Transference;
import com.example.banktransfer.infrastructure.db.jpa.model.User;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.entity.builder.TransferenceEntityBuilder;
import com.example.banktransfer.module.transference.v1.repository.implementation.jpa.mapper.protocol.ITransferenceMapper;

public class TransferenceMapper implements ITransferenceMapper {

    @Override
    public TransferenceEntity toEntity(Transference raw) {
        return new TransferenceEntityBuilder() //
                .withId(raw.getId()) //
                .withAmount(raw.getAmount()) //
                .withType(raw.getType()) //
                .withPayerId(raw.getPayer().getId()) //
                .withPayeeId(raw.getPayee().getId()) //
                .withCreatedAt(raw.getCreatedAt()) //
                .withUpdatedAt(raw.getUpdatedAt()) //
                .withDeletedAt(raw.getDeletedAt()) //
                .build();
    }

    @Override
    public Transference toPersistence(TransferenceEntity entity) {
        User payer = new User();
        payer.setId(entity.getPayerId());
        User payee = new User();
        payer.setId(entity.getPayeeId());

        return new Transference(
                entity.getId(), //
                entity.getAmount(), //
                payer, payee, //
                entity.getType(), //
                entity.getCreatedAt(), //
                entity.getUpdatedAt(), //
                entity.getDeletedAt() //
        );
    }
}
