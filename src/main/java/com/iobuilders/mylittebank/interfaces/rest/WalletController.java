package com.iobuilders.mylittebank.interfaces.rest;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.services.UserService;
import com.iobuilders.mylittebank.domain.services.WalletService;
import com.iobuilders.mylittebank.interfaces.rest.dto.WalletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Wallets")
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;


    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest walletRequest) {
        User user = userService.getUser(walletRequest.getUserId());
        Wallet Wallet = null;
        try {
            Wallet = walletService.createWallet(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(Wallet);
    }
}
