package com.iobuilders.mylittebank.application.dto.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.application.dto.response.BalanceTransactionsByWalletResponse;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BalanceTransactionsByWalletResponseMapper.class})
@ExtendWith(SpringExtension.class)
class BalanceTransactionsByWalletMyLittleBankResponseMapperTest {
    @Autowired
    private BalanceTransactionsByWalletResponseMapper balanceTransactionsByWalletResponseMapper;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link BalanceTransactionsByWalletResponseMapper#toTransactionsBalanceByWalletResponseUser(Wallet)}
     */
    @Test
    void testToTransactionsBalanceByWalletResponseUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        BigDecimal balance = BigDecimal.valueOf(1L);
        wallet.setBalance(balance);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        wallet.setTransactionList(transactionList);
        wallet.setUser(user);
        wallet.setWalletId(1L);
        BalanceTransactionsByWalletResponse actualToTransactionsBalanceByWalletResponseUserResult = balanceTransactionsByWalletResponseMapper
                .toTransactionsBalanceByWalletResponseUser(wallet);
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = actualToTransactionsBalanceByWalletResponseUserResult.getBalance();
        assertSame(expectedBalance, balance2);
        assertEquals(1L, actualToTransactionsBalanceByWalletResponseUserResult.getWalletId());
        assertEquals(transactionList, actualToTransactionsBalanceByWalletResponseUserResult.getTransactionResponseList());
        assertEquals("1", balance2.toString());
    }

    /**
     * Method under test: {@link BalanceTransactionsByWalletResponseMapper#toTransactionsBalanceByWalletResponseUser(Wallet)}
     */
    @Test
    void testToTransactionsBalanceByWalletResponseUser2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        Wallet wallet = mock(Wallet.class);
        when(wallet.getBalance()).thenReturn(BigDecimal.valueOf(1L));
        when(wallet.getTransactionList()).thenReturn(new ArrayList<>());
        when(wallet.getWalletId()).thenReturn(1L);
        doNothing().when(wallet).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        doNothing().when(wallet).setUser(Mockito.<User>any());
        doNothing().when(wallet).setWalletId(anyLong());
        BigDecimal balance = BigDecimal.valueOf(1L);
        wallet.setBalance(balance);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        wallet.setTransactionList(transactionList);
        wallet.setUser(user);
        wallet.setWalletId(1L);
        BalanceTransactionsByWalletResponse actualToTransactionsBalanceByWalletResponseUserResult = balanceTransactionsByWalletResponseMapper
                .toTransactionsBalanceByWalletResponseUser(wallet);
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = actualToTransactionsBalanceByWalletResponseUserResult.getBalance();
        assertSame(expectedBalance, balance2);
        assertEquals(1L, actualToTransactionsBalanceByWalletResponseUserResult.getWalletId());
        assertEquals(transactionList, actualToTransactionsBalanceByWalletResponseUserResult.getTransactionResponseList());
        assertEquals("1", balance2.toString());
        verify(wallet).getBalance();
        verify(wallet).getTransactionList();
        verify(wallet).getWalletId();
        verify(wallet).setBalance(Mockito.<BigDecimal>any());
        verify(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        verify(wallet).setUser(Mockito.<User>any());
        verify(wallet).setWalletId(anyLong());
    }

    /**
     * Method under test: {@link BalanceTransactionsByWalletResponseMapper#toTransactionsBalanceByWalletResponseUser(Wallet)}
     */
    @Test
    void testToTransactionsBalanceByWalletResponseUser3() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

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

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Wallet wallet = mock(Wallet.class);
        when(wallet.getBalance()).thenReturn(BigDecimal.valueOf(1L));
        when(wallet.getTransactionList()).thenReturn(transactionList);
        when(wallet.getWalletId()).thenReturn(1L);
        doNothing().when(wallet).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        doNothing().when(wallet).setUser(Mockito.<User>any());
        doNothing().when(wallet).setWalletId(anyLong());
        BigDecimal balance = BigDecimal.valueOf(1L);
        wallet.setBalance(balance);
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        BalanceTransactionsByWalletResponse actualToTransactionsBalanceByWalletResponseUserResult = balanceTransactionsByWalletResponseMapper
                .toTransactionsBalanceByWalletResponseUser(wallet);
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = actualToTransactionsBalanceByWalletResponseUserResult.getBalance();
        assertSame(expectedBalance, balance2);
        assertEquals(1L, actualToTransactionsBalanceByWalletResponseUserResult.getWalletId());
        assertEquals(1, actualToTransactionsBalanceByWalletResponseUserResult.getTransactionResponseList().size());
        assertEquals("1", balance2.toString());
        verify(wallet).getBalance();
        verify(wallet).getTransactionList();
        verify(wallet, atLeast(1)).getWalletId();
        verify(wallet).setBalance(Mockito.<BigDecimal>any());
        verify(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        verify(wallet).setUser(Mockito.<User>any());
        verify(wallet).setWalletId(anyLong());
    }

    /**
     * Method under test: {@link BalanceTransactionsByWalletResponseMapper#toTransactionsBalanceByWalletResponseUser(Wallet)}
     */
    @Test
    void testToTransactionsBalanceByWalletResponseUser4() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

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

        User user4 = new User();
        user4.setEmail("john.smith@example.org");
        user4.setName("42");
        user4.setPhoneNumber("8605550118");
        user4.setUserId(2L);
        user4.setWallet(new HashSet<>());

        Wallet destinationWallet2 = new Wallet();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setTransactionList(new ArrayList<>());
        destinationWallet2.setUser(user4);
        destinationWallet2.setWalletId(2L);

        User user5 = new User();
        user5.setEmail("john.smith@example.org");
        user5.setName("42");
        user5.setPhoneNumber("8605550118");
        user5.setUserId(2L);
        user5.setWallet(new HashSet<>());

        Wallet originWallet2 = new Wallet();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setTransactionList(new ArrayList<>());
        originWallet2.setUser(user5);
        originWallet2.setWalletId(2L);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(BigDecimal.valueOf(1L));
        transaction2.setDestinationWallet(destinationWallet2);
        transaction2.setOriginWallet(originWallet2);
        transaction2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setTransactionId(2L);
        transaction2.setTransactionType("42");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction2);
        transactionList.add(transaction);
        Wallet wallet = mock(Wallet.class);
        when(wallet.getBalance()).thenReturn(BigDecimal.valueOf(1L));
        when(wallet.getTransactionList()).thenReturn(transactionList);
        when(wallet.getWalletId()).thenReturn(1L);
        doNothing().when(wallet).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        doNothing().when(wallet).setUser(Mockito.<User>any());
        doNothing().when(wallet).setWalletId(anyLong());
        BigDecimal balance = BigDecimal.valueOf(1L);
        wallet.setBalance(balance);
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user);
        wallet.setWalletId(1L);
        BalanceTransactionsByWalletResponse actualToTransactionsBalanceByWalletResponseUserResult = balanceTransactionsByWalletResponseMapper
                .toTransactionsBalanceByWalletResponseUser(wallet);
        BigDecimal expectedBalance = balance.ONE;
        BigDecimal balance2 = actualToTransactionsBalanceByWalletResponseUserResult.getBalance();
        assertSame(expectedBalance, balance2);
        assertEquals(1L, actualToTransactionsBalanceByWalletResponseUserResult.getWalletId());
        assertEquals(2, actualToTransactionsBalanceByWalletResponseUserResult.getTransactionResponseList().size());
        assertEquals("1", balance2.toString());
        verify(wallet).getBalance();
        verify(wallet).getTransactionList();
        verify(wallet, atLeast(1)).getWalletId();
        verify(wallet).setBalance(Mockito.<BigDecimal>any());
        verify(wallet).setTransactionList(Mockito.<List<Transaction>>any());
        verify(wallet).setUser(Mockito.<User>any());
        verify(wallet).setWalletId(anyLong());
    }

    /**
     * Method under test: {@link BalanceTransactionsByWalletResponseMapper#toTransactionsBalanceByWalletResponseUser(Wallet)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testToTransactionsBalanceByWalletResponseUser5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper.lambda$toTransactionsBalanceByWalletResponseUser$0(BalanceTransactionsByWalletResponseMapper.java:28)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1655)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
        //       at com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(BalanceTransactionsByWalletResponseMapper.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

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

        User user4 = new User();
        user4.setEmail("jane.doe@example.org");
        user4.setName("Name");
        user4.setPhoneNumber("6625550144");
        user4.setUserId(1L);
        user4.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(new ArrayList<>());
        wallet.setUser(user4);
        wallet.setWalletId(1L);

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setName("Name");
        user5.setPhoneNumber("6625550144");
        user5.setUserId(1L);
        user5.setWallet(new HashSet<>());

        Wallet wallet2 = new Wallet();
        wallet2.setBalance(BigDecimal.valueOf(1L));
        wallet2.setTransactionList(new ArrayList<>());
        wallet2.setUser(user5);
        wallet2.setWalletId(1L);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getOriginWallet()).thenReturn(wallet2);
        when(transaction.getDestinationWallet()).thenReturn(wallet);
        when(transaction.getTransactionType()).thenReturn("Transaction Type");
        when(transaction.getAmount()).thenReturn(null);
        when(transaction.getTransactionDateTime()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(transaction.getTransactionId()).thenReturn(1L);
        doNothing().when(transaction).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(transaction).setDestinationWallet(Mockito.<Wallet>any());
        doNothing().when(transaction).setOriginWallet(Mockito.<Wallet>any());
        doNothing().when(transaction).setTransactionDateTime(Mockito.<LocalDateTime>any());
        doNothing().when(transaction).setTransactionId(anyLong());
        doNothing().when(transaction).setTransactionType(Mockito.<String>any());
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        Wallet wallet3 = mock(Wallet.class);
        when(wallet3.getBalance()).thenReturn(BigDecimal.valueOf(1L));
        when(wallet3.getTransactionList()).thenReturn(transactionList);
        when(wallet3.getWalletId()).thenReturn(1L);
        doNothing().when(wallet3).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(wallet3).setTransactionList(Mockito.<List<Transaction>>any());
        doNothing().when(wallet3).setUser(Mockito.<User>any());
        doNothing().when(wallet3).setWalletId(anyLong());
        wallet3.setBalance(BigDecimal.valueOf(1L));
        wallet3.setTransactionList(new ArrayList<>());
        wallet3.setUser(user);
        wallet3.setWalletId(1L);
        balanceTransactionsByWalletResponseMapper.toTransactionsBalanceByWalletResponseUser(wallet3);
    }
}

