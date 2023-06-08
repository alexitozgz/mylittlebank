package com.iobuilders.mylittebank.application.rest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeDepositRequestMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeTransferRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private MakeDepositRequestMapper makeDepositRequestMapper;

    @MockBean
    private MakeDepositUseCase makeDepositUseCase;

    @MockBean
    private MakeTransferRequestMapper makeTransferRequestMapper;

    @MockBean
    private MakeTransferUseCase makeTransferUseCase;

    @Autowired
    private TransactionController transactionController;

    /**
     * Method under test: {@link TransactionController#makeDeposit(MakeDepositRequest)}
     */
    @Test
    void testMakeDeposit() throws Exception {
        doNothing().when(makeDepositUseCase).makeDeposit(Mockito.<Transaction>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        when(makeDepositRequestMapper.toTransaction(Mockito.<MakeDepositRequest>any())).thenReturn(transaction);

        MakeDepositRequest makeDepositRequest = new MakeDepositRequest();
        makeDepositRequest.setAmount(BigDecimal.valueOf(1L));
        makeDepositRequest.setDestinationWalletId(1L);
        String content = (new ObjectMapper()).writeValueAsString(makeDepositRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Deposit created")));
    }

    /**
     * Method under test: {@link TransactionController#makeTransfer(MakeTransferRequest)}
     */
    @Test
    void testMakeTransfer() throws NotEnoughMoneyException, Exception {
        doNothing().when(makeTransferUseCase).makeTransfer(Mockito.<Transaction>any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());

        Wallet destinationWallet = new Wallet();
        destinationWallet.setBalance(BigDecimal.valueOf(1L));
        destinationWallet.setTransactionList(new ArrayList<>());
        destinationWallet.setUser(user);
        destinationWallet.setWalletId(1L);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(1L));
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("Transaction Type");
        when(makeTransferRequestMapper.toTransaction(Mockito.<MakeTransferRequest>any())).thenReturn(transaction);

        MakeTransferRequest makeTransferRequest = new MakeTransferRequest();
        makeTransferRequest.setAmount(BigDecimal.valueOf(1L));
        makeTransferRequest.setDestinationWalletId(1L);
        makeTransferRequest.setOriginWalletId(1L);
        String content = (new ObjectMapper()).writeValueAsString(makeTransferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Transfer created")));
    }
}

