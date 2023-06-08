package com.iobuilders.mylittebank.infrastructure.persistence.adapters;

import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.UserEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.entity.WalletEntity;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;

import java.math.BigDecimal;

public class WalletPersistenceAdapter implements CreateWalletPort, ObtainWalletPort, UpdateWalletPort {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;


    public WalletPersistenceAdapter(WalletRepository walletRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
    }


    @Override
    public Long createWallet(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUser(userEntity);
        walletEntity.setBalance(BigDecimal.ZERO);
        return walletRepository.save(walletEntity).getWalletId();
    }

    @Override
    public Wallet obtainWallet(Long walletId) throws WalletNotFoundException {
        WalletEntity walletEntity = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException(walletId));
        return walletMapper.toWallet(walletEntity);
    }

    @Override
    public void updateWallet(Wallet wallet) {
        WalletEntity walletEntity = walletMapper.toWalletEntity(wallet);
        walletRepository.save(walletEntity);
    }
}
