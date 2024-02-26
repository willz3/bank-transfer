package com.example.banktransfer.module.transference.v1.dto.response;

import java.math.BigDecimal;
import java.util.Date;

public record CreateTransferenceResponseDTO(Long id, BigDecimal amount, Long payerId, Long payeeId, Date createdAt) {
}
