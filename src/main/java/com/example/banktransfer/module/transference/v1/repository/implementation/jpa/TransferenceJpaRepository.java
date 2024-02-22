package com.example.banktransfer.module.transference.v1.repository.implementation.jpa;

import com.example.banktransfer.infrastructure.db.jpa.model.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenceJpaRepository extends JpaRepository<Transference, Long> {
}
