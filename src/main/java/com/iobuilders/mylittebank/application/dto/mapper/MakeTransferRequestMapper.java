package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;

public class MakeTransferRequestMapper {

    public Transaction toTransaction (MakeTransferRequest makeTransferRequest){
        Transaction transaction = new Transaction();

        Wallet destinationWallet = new Wallet();
        destinationWallet.setWalletId(makeTransferRequest.getDestinationWalletId());
        transaction.setDestinationWallet(destinationWallet);

        Wallet originWallet = new Wallet();
        originWallet.setWalletId(makeTransferRequest.getOriginWalletId());
        transaction.setOriginWallet(originWallet);

        transaction.setAmount(makeTransferRequest.getAmount());

        return transaction;
    }
}
