package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;

public interface CreateWalletUseCase {
    Long createWallet(Long userId) throws UserNotFoundException;
}
