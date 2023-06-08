package com.iobuilders.mylittebank.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeDepositRequestMapper;
import com.iobuilders.mylittebank.application.dto.mapper.MakeTransferRequestMapper;
import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.application.dto.request.MakeTransferRequest;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeDepositUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.MakeTransferUseCase;
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
import org.springframework.web.util.NestedServletException;

import java.math.BigDecimal;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateDeposit;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateTransfer;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


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

    @Test
    void makeDeposit_ok() throws Exception {
        doReturn(1L).when(makeDepositUseCase).makeDeposit(Mockito.any());

        User user = generateUser();

        Wallet destinationWallet = generateWallet(user);

        Transaction transaction = generateDeposit(destinationWallet, BigDecimal.TEN);

        MakeDepositRequest makeDepositRequest = new MakeDepositRequest();
        makeDepositRequest.setAmount(BigDecimal.valueOf(1L));
        makeDepositRequest.setDestinationWalletId(1L);

        when(makeDepositRequestMapper.toTransaction(makeDepositRequest)).thenReturn(transaction);

        String content = (new ObjectMapper()).writeValueAsString(makeDepositRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Deposit created")));
    }

    @Test
    void makeTransfer_ok() throws Exception {
        doReturn(1L).when(makeTransferUseCase).makeTransfer(Mockito.any());

        User user = generateUser();

        Wallet destinationWallet = generateWallet(user);

        Wallet originWallet = generateWallet(user);


        Transaction transaction = generateTransfer(destinationWallet,originWallet, BigDecimal.TEN);


        MakeTransferRequest makeTransferRequest = new MakeTransferRequest();
        makeTransferRequest.setAmount(BigDecimal.valueOf(1L));
        makeTransferRequest.setDestinationWalletId(1L);
        makeTransferRequest.setOriginWalletId(1L);

        when(makeTransferRequestMapper.toTransaction(makeTransferRequest)).thenReturn(transaction);

        String content = (new ObjectMapper()).writeValueAsString(makeTransferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Transfer created")));
    }

    @Test
    void makeDeposit_validation_ko() throws Exception {
        MakeDepositRequest makeDepositRequest = new MakeDepositRequest();

        String content = (new ObjectMapper()).writeValueAsString(makeDepositRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void makeTransfer_validation_ko() throws Exception {
        MakeTransferRequest makeTransferRequest = new MakeTransferRequest();

        String content = (new ObjectMapper()).writeValueAsString(makeTransferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void makeDeposit_walletNotFound() throws Exception {
        doThrow(WalletNotFoundException.class).when(makeDepositUseCase).makeDeposit(Mockito.any());

        MakeDepositRequest makeDepositRequest = new MakeDepositRequest();

        when(makeDepositRequestMapper.toTransaction(makeDepositRequest)).thenReturn(new Transaction());

        String content = (new ObjectMapper()).writeValueAsString(makeDepositRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        try {
            MockMvcBuilders.standaloneSetup(transactionController)
                    .build()
                    .perform(requestBuilder);
        } catch (Exception e){
            assertEquals(NestedServletException.class, e.getClass());
            assertEquals(WalletNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    void makeTransfer_walletNotFound() throws Exception {
        doThrow(WalletNotFoundException.class).when(makeTransferUseCase).makeTransfer(Mockito.any());

        MakeTransferRequest makeTransferRequest = new MakeTransferRequest();

        when(makeTransferRequestMapper.toTransaction(makeTransferRequest)).thenReturn(new Transaction());

        String content = (new ObjectMapper()).writeValueAsString(makeTransferRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        try {
            MockMvcBuilders.standaloneSetup(transactionController)
                    .build()
                    .perform(requestBuilder);
        } catch (Exception e){
            assertEquals(NestedServletException.class, e.getClass());
            assertEquals(WalletNotFoundException.class, e.getCause().getClass());
        }
    }

    @Test
    void makeTransfer_methodNotAllowed() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(transactionController).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void makeDeposit_methodNotAllowed() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/deposit")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(transactionController).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void resourceNotFound() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transactions/fake")
                .contentType(MediaType.APPLICATION_JSON);

        MockMvcBuilders.standaloneSetup(transactionController).build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(404));
    }



}

