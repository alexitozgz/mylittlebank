package com.iobuilders.mylittebank.domain.services;

import java.math.BigDecimal;

public interface DepositService {
    void makeDeposit(long accountId, BigDecimal amount);
}
