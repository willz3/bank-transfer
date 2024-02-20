package com.example.banktransfer.module.transference.v1.entity.builder;

import com.example.banktransfer.module.transference.v1.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Date;

public class UserEntityBuilder {
    private String name;
    private String document;
    private String email;
    private String password;
    private UserEntity.UserType type;
    private BigDecimal balance;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public UserEntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserEntityBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public UserEntityBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntityBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntityBuilder withType(UserEntity.UserType type) {
        this.type = type;
        return this;
    }

    public UserEntityBuilder withBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public UserEntityBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserEntityBuilder withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserEntityBuilder withDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public UserEntity build() {
        return new UserEntity(name, document, email, password, type, balance, createdAt, updatedAt, deletedAt);
    }
}