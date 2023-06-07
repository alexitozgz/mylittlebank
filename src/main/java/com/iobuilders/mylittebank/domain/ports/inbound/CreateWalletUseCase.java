package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;

public interface CreateWalletUseCase {
    void createWallet(Long userId) throws UserNotFoundException;
}
