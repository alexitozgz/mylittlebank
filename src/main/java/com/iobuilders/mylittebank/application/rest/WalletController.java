package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper;
import com.iobuilders.mylittebank.application.dto.request.CreateWalletRequest;
import com.iobuilders.mylittebank.application.dto.request.ObtainBalanceTransactionsByWalletRequest;
import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/wallets")
@Slf4j
public class WalletController {
    private final CreateWalletUseCase createWalletUseCase;
    private final ObtainBalanceTransactionsByWalletUseCase obtainBalanceTransactionsByWalletUseCase;
    private final BalanceTransactionsByWalletResponseMapper balanceTransactionsByWalletResponseMapper;

    public WalletController(CreateWalletUseCase createWalletUseCase, ObtainBalanceTransactionsByWalletUseCase obtainBalanceTransactionsByWalletUseCase, BalanceTransactionsByWalletResponseMapper balanceTransactionsByWalletResponseMapper) {
        this.createWalletUseCase = createWalletUseCase;
        this.obtainBalanceTransactionsByWalletUseCase = obtainBalanceTransactionsByWalletUseCase;
        this.balanceTransactionsByWalletResponseMapper = balanceTransactionsByWalletResponseMapper;
    }

    @PostMapping
    public ResponseEntity<Object> createWallet(@Valid @NotNull @RequestBody CreateWalletRequest createWalletRequest) throws UserNotFoundException {
        log.info("Starting createWallet {}", createWalletRequest);
        Long walletId = createWalletUseCase.createWallet(createWalletRequest.getUserId());
        log.info("Finishing createWallet successfully");
        return new ResponseEntity<>(new MyLittleBankResponse(HttpStatus.CREATED, "Wallet created with id "+walletId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getWallet(@Valid @NotNull @RequestBody ObtainBalanceTransactionsByWalletRequest obtainBalanceTransactionsByWalletRequest) throws WalletNotFoundException {
        log.info("Starting getWallet {}", obtainBalanceTransactionsByWalletRequest);
        Wallet wallet = obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(obtainBalanceTransactionsByWalletRequest.getWalletId());
        log.info("Finishing getWallet successfully");
        return ResponseEntity.ok(balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet));
    }

}
