package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Wallet;

public interface ObtainBalanceTransactionsByWalletUseCase {
    Wallet obtainBalanceTransactionsByWallet(Long walletId) throws WalletNotFoundException;
}
