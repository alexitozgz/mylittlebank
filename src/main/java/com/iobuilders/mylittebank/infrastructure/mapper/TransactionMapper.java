package com.iobuilders.mylittebank.infrastructure.mapper;

import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionEntity transactionToTransactionEntity(Transaction transaction){
        return modelMapper.map(transaction, TransactionEntity.class);
    }

    public Transaction transactionEntityToTransaction (TransactionEntity transactionEntity){
        return modelMapper.map(transactionEntity, Transaction.class);
    }

}
