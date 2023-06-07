package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.application.dto.response.TransactionResponse;
import com.iobuilders.mylittebank.domain.model.Wallet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BalanceTransactionsByWalletResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BalanceTransactionsByWalletResponse toTransactionsBalanceByWalletResponseUser (Wallet wallet){
        BalanceTransactionsByWalletResponse balanceTransactionsByWalletResponse = new BalanceTransactionsByWalletResponse();
        balanceTransactionsByWalletResponse.setBalance(wallet.getBalance());
        balanceTransactionsByWalletResponse.setWalletId(wallet.getWalletId());
        wallet.getTransactionList().stream().map(transaction -> {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionId(transaction.getTransactionId());
            transactionResponse.setTransactionType(transaction.getTransactionType());
            transactionResponse.setTransactionDateTime(transaction.getTransactionDateTime());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setDestinationWallet(transaction.getDestinationWallet().getWalletId());
            if (transaction.getOriginWallet()!=null){
                transactionResponse.setOriginWallet(transaction.getOriginWallet().getWalletId());
                if (transaction.getOriginWallet().getWalletId() == wallet.getWalletId()) {
                    transactionResponse.setAmount(transaction.getAmount().negate());
                }
            }
            return transactionResponse;
        }).forEach(balanceTransactionsByWalletResponse.getTransactionResponseList()::add);

        return balanceTransactionsByWalletResponse;
    }
}
