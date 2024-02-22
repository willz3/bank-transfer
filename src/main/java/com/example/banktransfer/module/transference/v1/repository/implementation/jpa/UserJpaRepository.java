package com.example.banktransfer.module.transference.v1.repository.implementation.jpa;

import com.example.banktransfer.infrastructure.db.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
