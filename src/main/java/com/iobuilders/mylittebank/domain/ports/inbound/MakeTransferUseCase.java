package com.iobuilders.mylittebank.domain.ports.inbound;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;

public interface MakeTransferUseCase {
    void makeTransfer(Transaction transaction) throws UserNotFoundException, NotEnoughMoneyException;
}