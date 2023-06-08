package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;

public interface MakeDepositUseCase {
    Long makeDeposit(Transaction transaction) throws WalletNotFoundException;
}
