package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.mapper.MakeDepositRequestMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeTransferRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final MakeDepositUseCase makeDepositUseCase;
    private final MakeTransferUseCase makeTransferUseCase;
    private final MakeTransferRequestMapper makeTransferRequestMapper;
    private final MakeDepositRequestMapper makeDepositRequestMapper;


    public TransactionController(MakeDepositUseCase makeDepositUseCase, MakeTransferUseCase makeTransferUseCase, MakeTransferRequestMapper makeTransferRequestMapper, MakeDepositRequestMapper makeDepositRequestMapper) {
        this.makeDepositUseCase = makeDepositUseCase;
        this.makeTransferUseCase = makeTransferUseCase;
        this.makeTransferRequestMapper = makeTransferRequestMapper;
        this.makeDepositRequestMapper = makeDepositRequestMapper;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Object> makeDeposit(@Valid @NotNull @RequestBody MakeDepositRequest makeDepositRequest) throws WalletNotFoundException {
        Transaction transaction = makeDepositRequestMapper.toTransaction(makeDepositRequest);
        makeDepositUseCase.makeDeposit(transaction);
        return ResponseEntity.ok(new MyLittleBankResponse(HttpStatus.CREATED, "Deposit created"));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> makeTransfer(@Valid @NotNull @RequestBody MakeTransferRequest makeTransferRequest) throws NotEnoughMoneyException, WalletNotFoundException {
        Transaction transaction = makeTransferRequestMapper.toTransaction(makeTransferRequest);
        makeTransferUseCase.makeTransfer(transaction);
        return ResponseEntity.ok(new MyLittleBankResponse(HttpStatus.CREATED, "Transfer created"));
    }
}
