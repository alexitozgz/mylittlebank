package com.iobuilders.mylittebank.domain.service;

import java.math.BigDecimal;

public interface TransferService {
    void makeTransfer(long sourceAccountId, long destinationAccountId, BigDecimal amount);
}
