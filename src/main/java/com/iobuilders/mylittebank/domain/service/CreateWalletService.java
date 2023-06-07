package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.RegisterUserUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;

public class CreateWalletService implements CreateWalletUseCase {

    private CreateWalletPort createWalletPort;

    public CreateWalletService(CreateWalletPort createWalletPort) {
        this.createWalletPort = createWalletPort;
    }

    @Override
    public void createWallet(Long userId) {
        //TODO check if the user exists
        createWalletPort.createWallet(userId);
    }
}
