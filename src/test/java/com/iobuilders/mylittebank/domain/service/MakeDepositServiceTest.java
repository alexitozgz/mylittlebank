package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
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

@ContextConfiguration(classes = {MakeDepositService.class})
@ExtendWith(SpringExtension.class)
class MakeDepositServiceTest {
    @MockBean
    private MakeDepositPort makeDepositPort;

    @Autowired
    private MakeDepositService makeDepositService;

    @MockBean
    private ObtainWalletPort obtainWalletPort;

    @MockBean
    private UpdateWalletPort updateWalletPort;

    /**
     * Method under test: {@link MakeDepositService#MakeDepositService(MakeDepositPort, ObtainWalletPort, UpdateWalletPort)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MakeDepositService.makeDepositPort
        //     MakeDepositService.obtainWalletPort
        //     MakeDepositService.updateWalletPort

        new MakeDepositService(mock(MakeDepositPort.class), mock(ObtainWalletPort.class), mock(UpdateWalletPort.class));

    }

    /**
     * Method under test: {@link MakeDepositService#makeDeposit(Transaction)}
     */
    @Test
    void testMakeDeposit() throws UserNotFoundException {
        doNothing().when(makeDepositPort).createDeposit(Mockito.<Transaction>any());

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
        makeDepositService.makeDeposit(transaction);
        verify(makeDepositPort).createDeposit(Mockito.<Transaction>any());
        verify(obtainWalletPort).obtainWalletPort(Mockito.<Long>any());
        verify(updateWalletPort).updateWallet(Mockito.<Wallet>any());
        assertEquals("DEPOSIT", transaction.getTransactionType());
    }

    /**
     * Method under test: {@link MakeDepositService#makeDeposit(Transaction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMakeDeposit2() throws UserNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.iobuilders.mylittebank.domain.service.MakeDepositService.makeDeposit(MakeDepositService.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(makeDepositPort).createDeposit(Mockito.<Transaction>any());

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
        makeDepositService.makeDeposit(transaction);
    }
}

