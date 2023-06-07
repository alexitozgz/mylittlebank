package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.DepositRequest;
import com.iobuilders.mylittebank.domain.service.DepositService;
import com.iobuilders.mylittebank.domain.service.TransferService;
import com.iobuilders.mylittebank.application.dto.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
//@RequestMapping("/transactions")
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
