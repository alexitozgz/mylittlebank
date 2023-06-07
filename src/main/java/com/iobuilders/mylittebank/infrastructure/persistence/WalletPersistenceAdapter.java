package com.iobuilders.mylittebank.infrastructure.persistence;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class WalletPersistenceAdapter implements CreateWalletPort {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;


    public WalletPersistenceAdapter(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }


    @Override
    public void createWallet(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUser(userEntity);
        walletEntity.setBalance(BigDecimal.ZERO);
        walletRepository.save(walletEntity);
    }

}
