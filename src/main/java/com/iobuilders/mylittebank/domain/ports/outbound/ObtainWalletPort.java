package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Wallet;

public interface ObtainWalletPort {
    Wallet obtainWallet(Long walletId) throws WalletNotFoundException;
}
