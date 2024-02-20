package com.example.banktransfer.module.transference.v1.entity;

import com.example.banktransfer.core.domain.Entity;
import com.example.banktransfer.module.transference.v1.entity.builder.UserEntityBuilder;

import java.math.BigDecimal;
import java.util.Date;

public class UserEntity extends Entity {
    private String name;
    private String document;
    private String email;
    private String password;
    private UserType type;
    private BigDecimal balance;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public UserEntity(String name, String document, String email, String password, UserType type, BigDecimal balance, Date createdAt, Date updatedAt, Date deletedAt) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public UserEntity(Long id, String name, String document, String email, String password, UserType type, BigDecimal balance, Date createdAt, Date updatedAt, Date deletedAt) {
        super(id);
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public enum UserType {
        COMMOM,
        MERCHANT
    }

    public UserEntity toDebit(BigDecimal value) {
        BigDecimal newBalance = this.balance.subtract(value);
        return new UserEntityBuilder()//
                .withName(getName())//
                .withDocument(getDocument())//
                .withEmail(getEmail())//
                .withType(getType())//
                .withBalance(newBalance)//
                .withCreatedAt(getCreatedAt())//
                .withUpdatedAt(getUpdatedAt())//
                .withDeletedAt(getDeletedAt())//
                .build();
    }

    public UserEntity toCredit(BigDecimal value) {
        BigDecimal newBalance = this.balance.add(value);
        return new UserEntityBuilder()//
                .withName(getName())//
                .withDocument(getDocument())//
                .withEmail(getEmail())//
                .withType(getType())//
                .withBalance(newBalance)//
                .withCreatedAt(getCreatedAt())//
                .withUpdatedAt(getUpdatedAt())//
                .withDeletedAt(getDeletedAt())//
                .build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
}
