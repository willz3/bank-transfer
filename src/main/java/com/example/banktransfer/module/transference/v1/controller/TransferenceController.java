package com.example.banktransfer.module.transference.v1.controller;

import com.example.banktransfer.core.shared.logic.Either;
import com.example.banktransfer.module.transference.v1.dto.request.CreateTransferenceRequestDTO;
import com.example.banktransfer.module.transference.v1.entity.TransferenceEntity;
import com.example.banktransfer.module.transference.v1.error.UnauthorizedTransactionError;
import com.example.banktransfer.module.transference.v1.mapper.presentation.CreateTransferenceMapper.ICreateTransferenceMapper;
import com.example.banktransfer.module.transference.v1.usecase.CreateTransference.ICreateTransferenceUseCase;
import org.springframework.http.HttpStatus;
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

    private final ICreateTransferenceUseCase useCase;

    public TransferenceController(ICreateTransferenceMapper mapper, ICreateTransferenceUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> handle(@RequestBody @Validated CreateTransferenceRequestDTO request) {
        TransferenceEntity transference = mapper.toEntity(request);

        Either<Error, TransferenceEntity> result = useCase.execute(transference);

        return prepareResponse(result);
    }

    private ResponseEntity<Object> prepareResponse(Either<Error, TransferenceEntity> result) {
        if (result.isLeft()) {
            if (result.getLeft() instanceof UnauthorizedTransactionError) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getLeft().getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getLeft().getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(result.getRight()));
    }
}
