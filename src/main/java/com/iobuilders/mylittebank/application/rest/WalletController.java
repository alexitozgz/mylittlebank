package com.iobuilders.mylittebank.application.rest;

import com.iobuilders.mylittebank.application.dto.WalletRequest;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.service.UserService;
import com.iobuilders.mylittebank.domain.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
//@RequestMapping("/Wallets")
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
