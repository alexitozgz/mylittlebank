package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper;
import com.iobuilders.mylittebank.application.dto.request.CreateWalletRequest;
import com.iobuilders.mylittebank.application.dto.request.ObtainBalanceTransactionsByWalletRequest;
import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
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
    public ResponseEntity<String> createWallet(@RequestBody CreateWalletRequest createWalletRequest) {
        try {
            createWalletUseCase.createWallet(createWalletRequest.getUserId());
            return ResponseEntity.ok("Wallet creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear Wallet "+e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<BalanceTransactionsByWalletResponse> getWallet(@RequestBody ObtainBalanceTransactionsByWalletRequest obtainBalanceTransactionsByWalletRequest) {
        try {
            Wallet wallet = obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(obtainBalanceTransactionsByWalletRequest.getWalletId());
            return ResponseEntity.ok(balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
