package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class WalletEntity {

    @Id
    @GeneratedValue
    private long walletId;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

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

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
