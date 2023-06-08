package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ObtainBalanceTransactionsByWalletUseCaseService.class})
@ExtendWith(SpringExtension.class)
class ObtainBalanceTransactionsByWalletUseCaseServiceTest {
    @Autowired
    private ObtainBalanceTransactionsByWalletUseCaseService obtainBalanceTransactionsByWalletUseCaseService;

    @MockBean
    private ObtainTransactionsByWalletPort obtainTransactionsByWalletPort;

    @MockBean
    private ObtainWalletPort obtainWalletPort;

    /**
     * Method under test: {@link ObtainBalanceTransactionsByWalletUseCaseService#ObtainBalanceTransactionsByWalletUseCaseService(ObtainTransactionsByWalletPort, ObtainWalletPort)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ObtainBalanceTransactionsByWalletUseCaseService.obtainTransactionsByWalletPort
        //     ObtainBalanceTransactionsByWalletUseCaseService.obtainWalletPort

        new ObtainBalanceTransactionsByWalletUseCaseService(mock(ObtainTransactionsByWalletPort.class),
                mock(ObtainWalletPort.class));

    }

    /**
     * Method under test: {@link ObtainBalanceTransactionsByWalletUseCaseService#obtainBalanceTransactionsByWallet(Long)}
     */
    @Test
    void testObtainBalanceTransactionsByWallet() throws WalletNotFoundException {
        when(obtainTransactionsByWalletPort.obtainTransactionsByWalletPort(Mockito.<Wallet>any()))
                .thenReturn(new ArrayList<>());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        ArrayList<Transaction> transactionList = new ArrayList<>();
        wallet.setTransactionList(transactionList);
        wallet.setUser(user);
        wallet.setWalletId(1L);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        Wallet actualObtainBalanceTransactionsByWalletResult = obtainBalanceTransactionsByWalletUseCaseService
                .obtainBalanceTransactionsByWallet(1L);
        assertSame(wallet, actualObtainBalanceTransactionsByWalletResult);
        assertEquals(transactionList, actualObtainBalanceTransactionsByWalletResult.getTransactionList());
        assertEquals("1", actualObtainBalanceTransactionsByWalletResult.getBalance().toString());
        verify(obtainTransactionsByWalletPort).obtainTransactionsByWalletPort(Mockito.<Wallet>any());
        verify(obtainWalletPort).obtainWalletPort(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ObtainBalanceTransactionsByWalletUseCaseService#obtainBalanceTransactionsByWallet(Long)}
     */
    @Test
    void testObtainBalanceTransactionsByWallet2() throws WalletNotFoundException {
        when(obtainTransactionsByWalletPort.obtainTransactionsByWalletPort(Mockito.any()))
                .thenReturn(new ArrayList<>());
        when(obtainWalletPort.obtainWalletPort(2L))
                .thenThrow(new WalletNotFoundException(2L));
        assertThrows(WalletNotFoundException.class,
                () -> obtainBalanceTransactionsByWalletUseCaseService.obtainBalanceTransactionsByWallet(2L));
        verify(obtainWalletPort).obtainWalletPort(Mockito.<Long>any());
    }
}

