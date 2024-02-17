package com.example.banktransfer.module.transference.v1.entity;

import com.example.banktransfer.core.domain.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferenceEntity extends Entity {

    private BigDecimal value;
    private Long payerId;
    private Long payeeId;

    public TransferenceEntity(BigDecimal value, Long payerId, Long payeeId) {
        this.value = value;
        this.payerId = payerId;
        this.payeeId = payeeId;
    }

    public TransferenceEntity(Long id, BigDecimal value, Long payerId, Long payeeId) {
        super(id);
        this.value = value;
        this.payerId = payerId;
        this.payeeId = payeeId;
    }

    public boolean makeTransference(UserEntity payer, UserEntity payee) {
        return true;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferenceEntity that = (TransferenceEntity) o;
        return Objects.equals(value, that.value) && Objects.equals(payerId, that.payerId) && Objects.equals(payeeId, that.payeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, payerId, payeeId);
    }
}
