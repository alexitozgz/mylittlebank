package com.iobuilders.mylittebank.domain.service;

import java.math.BigDecimal;

public interface DepositService {
    void makeDeposit(long accountId, BigDecimal amount);
}
