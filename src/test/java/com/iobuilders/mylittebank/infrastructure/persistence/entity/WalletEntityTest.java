package com.iobuilders.mylittebank.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class WalletEntityTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link WalletEntity}
     *   <li>{@link WalletEntity#setBalance(BigDecimal)}
     *   <li>{@link WalletEntity#setUser(UserEntity)}
     *   <li>{@link WalletEntity#setWalletId(long)}
     *   <li>{@link WalletEntity#getBalance()}
     *   <li>{@link WalletEntity#getUser()}
     *   <li>{@link WalletEntity#getWalletId()}
     * </ul>
     */
    @Test
    void testConstructor() {
        WalletEntity actualWalletEntity = new WalletEntity();
        BigDecimal balance = BigDecimal.valueOf(1L);
        actualWalletEntity.setBalance(balance);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setName("Name");
        userEntity.setPhoneNumber("6625550144");
        userEntity.setUserId(1L);
        userEntity.setWallet(new HashSet<>());
        actualWalletEntity.setUser(userEntity);
        actualWalletEntity.setWalletId(1L);
        BigDecimal expectedBalance = balance.ONE;
        assertSame(expectedBalance, actualWalletEntity.getBalance());
        assertSame(userEntity, actualWalletEntity.getUser());
        assertEquals(1L, actualWalletEntity.getWalletId());
    }
}

