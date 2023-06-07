package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.DepositRequest;
import com.iobuilders.mylittebank.application.dto.TransferRequest;
import com.iobuilders.mylittebank.domain.model.Deposit;
import com.iobuilders.mylittebank.domain.model.Transfer;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final MakeDepositUseCase makeDepositUseCase;
    private final MakeTransferUseCase makeTransferUseCase;


    public TransactionController(MakeDepositUseCase makeDepositUseCase, MakeTransferUseCase makeTransferUseCase) {
        this.makeDepositUseCase = makeDepositUseCase;
        this.makeTransferUseCase = makeTransferUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> makeDeposit(@RequestBody DepositRequest request) {
        try {
            Deposit deposit = new Deposit(request.getWalletId(), request.getAmount());
            makeDepositUseCase.makeDeposit(deposit);
            return ResponseEntity.ok("Depósito creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear depósito");
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequest request) {
        try {
            Transfer transfer = new Transfer(request.getWalletId(), request.getDestinationWalletId(), request.getAmount());
            makeTransferUseCase.makeTransfer(transfer);
            return ResponseEntity.ok("Transferencia creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear transferencia");
        }
    }
}
