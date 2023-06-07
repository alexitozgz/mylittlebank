package com.iobuilders.mylittebank.application.dto.response;

import com.iobuilders.mylittebank.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BalanceTransactionsByWalletResponse {
    private long walletId;
    private BigDecimal balance;
    private List<TransactionResponse> transactionResponseList;

    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionResponse> getTransactionResponseList() {
        if (transactionResponseList == null){
            transactionResponseList = new ArrayList<>();
        }
        return transactionResponseList;
    }

    public void setTransactionResponseList(List<TransactionResponse> transactionResponseList) {
        this.transactionResponseList = transactionResponseList;
    }
}
