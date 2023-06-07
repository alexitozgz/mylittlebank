package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class WalletPersistenceAdapter implements CreateWalletPort, ObtainWalletPort, UpdateWalletPort {

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

    @Override
    public Wallet obtainWalletPort(Long walletId) throws UserNotFoundException {
        WalletEntity walletEntity = walletRepository.findById(walletId).orElseThrow(()->new UserNotFoundException("User not found"));
        return walletMapper.toWallet(walletEntity);
    }

    @Override
    public void updateWallet(Wallet wallet) {
        WalletEntity walletEntity = walletMapper.toWalletEntity(wallet);
        walletRepository.save(walletEntity);
    }
}
