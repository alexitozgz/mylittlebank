package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
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

    /**
     * Method under test: {@link MakeTransferService#MakeTransferService(MakeTransferPort, ObtainWalletPort, UpdateWalletPort)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MakeTransferService.makeTransferPort
        //     MakeTransferService.obtainWalletPort
        //     MakeTransferService.updateWalletPort

        new MakeTransferService(mock(MakeTransferPort.class), mock(ObtainWalletPort.class), mock(UpdateWalletPort.class));

    }

    /**
     * Method under test: {@link MakeTransferService#makeTransfer(Transaction)}
     */
    @Test
    void testMakeTransfer() throws NotEnoughMoneyException, UserNotFoundException {
        doNothing().when(makeTransferPort).createTransfer(Mockito.<Transaction>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user2);
        destinationWallet.setWalletId(1L);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user3);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        makeTransferService.makeTransfer(transaction);
        verify(makeTransferPort).createTransfer(Mockito.<Transaction>any());
        verify(obtainWalletPort, atLeast(1)).obtainWalletPort(Mockito.<Long>any());
        verify(updateWalletPort, atLeast(1)).updateWallet(Mockito.<Wallet>any());
        assertEquals("TRANSFER", transaction.getTransactionType());
    }

    /**
     * Method under test: {@link MakeTransferService#makeTransfer(Transaction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMakeTransfer2() throws NotEnoughMoneyException, UserNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.math.BigDecimal.compareTo(BigDecimal.java:3079)
        //       at com.iobuilders.mylittebank.domain.service.MakeTransferService.hasOriginBalanceEnough(MakeTransferService.java:42)
        //       at com.iobuilders.mylittebank.domain.service.MakeTransferService.makeTransfer(MakeTransferService.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(makeTransferPort).createTransfer(Mockito.<Transaction>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        Wallet wallet = mock(Wallet.class);
        when(wallet.getBalance()).thenReturn(null);
        doNothing().when(wallet).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        doNothing().when(wallet).setUser(Mockito.<User>any());
        doNothing().when(wallet).setWalletId(anyLong());
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user2);
        destinationWallet.setWalletId(1L);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user3);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        makeTransferService.makeTransfer(transaction);
    }
}

