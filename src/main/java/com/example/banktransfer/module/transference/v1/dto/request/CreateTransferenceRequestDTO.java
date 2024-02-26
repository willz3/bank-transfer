package com.example.banktransfer.module.transference.v1.dto.request;

import java.math.BigDecimal;

public record CreateTransferenceRequestDTO(BigDecimal value, Long payer, Long payee){}