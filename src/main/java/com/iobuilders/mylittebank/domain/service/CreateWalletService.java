package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;

public class CreateWalletService implements CreateWalletUseCase {

    private final CreateWalletPort createWalletPort;
    private final ObtainUserPort obtainUserPort;

    public CreateWalletService(CreateWalletPort createWalletPort, ObtainUserPort obtainUserPort) {
        this.createWalletPort = createWalletPort;
        this.obtainUserPort = obtainUserPort;
    }

    @Override
    public void createWallet(Long userId) throws UserNotFoundException {
        obtainUserPort.obtainUser(userId);
        createWalletPort.createWallet(userId);
    }
}
