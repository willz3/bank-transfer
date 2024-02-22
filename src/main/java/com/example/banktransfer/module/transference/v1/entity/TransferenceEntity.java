package com.example.banktransfer.module.transference.v1.entity;

import com.example.banktransfer.core.domain.Entity;
import com.example.banktransfer.module.transference.v1.entity.builder.TransferenceEntityBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class TransferenceEntity extends Entity {

    private BigDecimal amount;

    private Long payerId;

    private Long payeeId;

    private TransferenceType type;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public TransferenceEntity(Long id, BigDecimal amount, Long payerId, Long payeeId, TransferenceType type, Date createdAt, Date updatedAt, Date deletedAt) {
        super(id);
        this.amount = amount;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public TransferenceEntity(BigDecimal amount, Long payerId, Long payeeId, TransferenceType type) {
        this.amount = amount;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.type = type;
    }

    public enum TransferenceType {
        DEBIT,
        CREDIT
    }

    public boolean validateDebit(UserEntity payer) {
        return payer.getBalance().compareTo(getAmount()) >= 0;
    }

    public TransferenceEntity toCredit() {
        return new TransferenceEntityBuilder()//
                .withPayerId(getPayeeId())//
                .withPayeeId(getPayerId())//
                .withAmount(getAmount())//
                .withType(TransferenceType.CREDIT)//
                .build();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public TransferenceType getType() {
        return type;
    }

    public void setType(TransferenceType type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferenceEntity that = (TransferenceEntity) o;
        return Objects.equals(amount, that.amount) && Objects.equals(payerId, that.payerId) && Objects.equals(payeeId, that.payeeId) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, payerId, payeeId);
    }
}
