package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.Wallet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MakeDepositRequestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Transaction toTransaction (MakeDepositRequest makeDepositRequest){
        return modelMapper.map(makeDepositRequest, Transaction.class);
    }
}
