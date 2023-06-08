package com.iobuilders.mylittebank.domain.exceptions;

import java.math.BigDecimal;

public class NotEnoughMoneyException extends MyLittleBankGenericException {
    public NotEnoughMoneyException(BigDecimal balance, BigDecimal amount) {
        super("The origin account has not enough balance ("+balance+") to make the operation for the required amount("+amount+")");
    }
}
