package com.iobuilders.mylittebank.infrastructure.mapper;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class WalletMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Wallet toWallet (WalletEntity walletEntity){
        return modelMapper.map(walletEntity, Wallet.class);
    }

    public WalletEntity toWalletEntity (Wallet wallet){
        return modelMapper.map(wallet, WalletEntity.class);
    }
}
