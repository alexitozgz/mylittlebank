package com.iobuilders.mylittebank.domain.ports.outbound;

import com.iobuilders.mylittebank.domain.model.Wallet;

public interface UpdateWalletPort {
    void updateWallet(Wallet wallet);
}
