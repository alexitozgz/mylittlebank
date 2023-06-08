package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateWalletService implements CreateWalletUseCase {

    private final CreateWalletPort createWalletPort;
    private final ObtainUserPort obtainUserPort;

    public CreateWalletService(CreateWalletPort createWalletPort, ObtainUserPort obtainUserPort) {
        this.createWalletPort = createWalletPort;
        this.obtainUserPort = obtainUserPort;
    }

    @Override
    public Long createWallet(Long userId) throws UserNotFoundException {
        log.debug("Starting domain service createWallet with userid {}", userId);
        obtainUserPort.existsUser(userId);
        return createWalletPort.createWallet(userId);
    }
}
