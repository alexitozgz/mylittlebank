package com.iobuilders.mylittebank.domain.service;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateTransfer;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MakeTransferService.class})
@ExtendWith(SpringExtension.class)
class MakeTransferServiceTest {
    @MockBean
    private MakeTransferPort makeTransferPort;

    @Autowired
    private MakeTransferService makeTransferService;

    @MockBean
    private ObtainWalletPort obtainWalletPort;

    @MockBean
    private UpdateWalletPort updateWalletPort;

    @Test
    void makeTransfer_ok() throws WalletNotFoundException, NotEnoughMoneyException {
        User user = generateUser();
        Wallet destinationWallet = generateWallet(user);
        Wallet originWallet = generateWallet(user);
        originWallet.setWalletId(2);
        originWallet.setBalance(BigDecimal.valueOf(100));

        Transaction transaction = generateTransfer(destinationWallet, originWallet, BigDecimal.TEN);

        when(obtainWalletPort.obtainWallet(originWallet.getWalletId())).thenReturn(originWallet);
        when(obtainWalletPort.obtainWallet(destinationWallet.getWalletId())).thenReturn(destinationWallet);
        when(makeTransferPort.createTransfer(transaction)).thenReturn(33L);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        assertEquals(BigDecimal.ZERO, destinationWallet.getBalance());
        assertEquals(33L, makeTransferService.makeTransfer(transaction));
        assertEquals(BigDecimal.TEN, destinationWallet.getBalance());
    }

    @Test
    void makeTransfer_verify_port_calls() throws WalletNotFoundException, NotEnoughMoneyException {
        User user = generateUser();
        Wallet destinationWallet = generateWallet(user);
        Wallet originWallet = generateWallet(user);
        originWallet.setWalletId(2);
        originWallet.setBalance(BigDecimal.valueOf(100));

        Transaction transaction = generateTransfer(destinationWallet, originWallet, BigDecimal.TEN);

        when(obtainWalletPort.obtainWallet(originWallet.getWalletId())).thenReturn(originWallet);
        when(obtainWalletPort.obtainWallet(destinationWallet.getWalletId())).thenReturn(destinationWallet);
        when(makeTransferPort.createTransfer(transaction)).thenReturn(33L);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        makeTransferService.makeTransfer(transaction);

        verify(makeTransferPort).createTransfer(transaction);
        verify(obtainWalletPort).obtainWallet(transaction.getDestinationWallet().getWalletId());
        verify(updateWalletPort).updateWallet(transaction.getDestinationWallet());
    }

    @Test
    void makeTransfer_notEnoughMoneyException() throws WalletNotFoundException, NotEnoughMoneyException {
        User user = generateUser();
        Wallet destinationWallet = generateWallet(user);
        Wallet originWallet = generateWallet(user);
        originWallet.setBalance(BigDecimal.valueOf(100));

        Transaction transaction = generateTransfer(destinationWallet, originWallet, BigDecimal.TEN);

        when(obtainWalletPort.obtainWallet(destinationWallet.getWalletId())).thenReturn(destinationWallet);

        assertThrows(NotEnoughMoneyException.class, () -> makeTransferService.makeTransfer(transaction));

    }

    @Test
    void makeTransfer_walletNotFoundException() throws  WalletNotFoundException {
        Transaction transaction = generateTransfer(generateWallet(generateUser()),generateWallet(generateUser()),BigDecimal.TEN);
        doThrow(new WalletNotFoundException(transaction.getDestinationWallet().getWalletId())).when(obtainWalletPort).obtainWallet(transaction.getDestinationWallet().getWalletId());
        assertThrows(WalletNotFoundException.class, () -> makeTransferService.makeTransfer(transaction));
    }
}

