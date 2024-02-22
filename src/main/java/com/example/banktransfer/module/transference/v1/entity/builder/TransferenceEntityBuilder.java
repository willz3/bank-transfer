package com.example.banktransfer.module.transference.v1.entity.builder;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;

import java.math.BigDecimal;
import java.util.Date;

public class TransferenceEntityBuilder implements IBuilder {

    private Long id;

    private BigDecimal amount;

    private Long payerId;

    private Long payeeId;

    private TransferenceEntity.TransferenceType type;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public TransferenceEntityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TransferenceEntityBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }

    public TransferenceEntityBuilder withPayerId(Long payerId) {
        this.payerId = payerId;
        return this;
    }

    public TransferenceEntityBuilder withPayeeId(Long payeeId) {
        this.payeeId = payeeId;
        return this;
    }

    public TransferenceEntityBuilder withType(TransferenceEntity.TransferenceType type) {
        this.type = type;
        return this;
    }

    public TransferenceEntityBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TransferenceEntityBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public TransferenceEntityBuilder withDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    @Override
    public TransferenceEntity build() {
        return new TransferenceEntity(id, amount, payerId, payeeId, type, createdAt, updatedAt, deletedAt);
    }
}
