package com.iobuilders.mylittebank.interfaces.rest;

import com.iobuilders.mylittebank.domain.services.DepositService;
import com.iobuilders.mylittebank.domain.services.TransferService;
import com.iobuilders.mylittebank.interfaces.rest.dto.DepositRequest;
import com.iobuilders.mylittebank.interfaces.rest.dto.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final DepositService depositService;
    private final TransferService transferService;


    public TransactionController(DepositService depositService, TransferService transferService) {
        this.depositService = depositService;
        this.transferService =  transferService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> makeDeposit(@RequestBody DepositRequest request) {
        depositService.makeDeposit(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> makeTransfer(@RequestBody TransferRequest request) {
        transferService.makeTransfer(request.getSourceAccountId(), request.getDestinationAccountId(), request.getAmount());
        return ResponseEntity.ok().build();
    }
}
