package com.example.banktransfer.infrastructure.db.jpa.model;

import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "transference")
@Table(name = "transference")
public class Transference implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @Enumerated(EnumType.STRING)
    private TransferenceEntity.TransferenceType type;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;

    public Transference() {
    }

    public Transference(Long id, BigDecimal amount, User payer, User payee, TransferenceEntity.TransferenceType type, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.amount = amount;
        this.payer = payer;
        this.payee = payee;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public TransferenceEntity.TransferenceType getType() {
        return type;
    }

    public void setType(TransferenceEntity.TransferenceType type) {
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
        Transference that = (Transference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
