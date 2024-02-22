package com.example.banktransfer.module.transference.v1.controller;

import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transfer")
public class TransferenceController {

    private final ICreateTransferenceMapper mapper;

    public TransferenceController(ICreateTransferenceMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping("/")
    public ResponseEntity<Object> handle(@RequestBody @Validated CreateTransferenceDTO request) {
        TransferenceEntity transference = mapper.toEntity(request);

        return ResponseEntity.noContent().build();
    }
}
