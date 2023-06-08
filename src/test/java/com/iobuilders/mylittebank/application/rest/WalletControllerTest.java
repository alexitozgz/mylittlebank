package com.iobuilders.mylittebank.application.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper;
import com.iobuilders.mylittebank.application.dto.request.CreateWalletRequest;
import com.iobuilders.mylittebank.application.dto.request.ObtainBalanceTransactionsByWalletRequest;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.*;
@ContextConfiguration(classes = {WalletController.class, BalanceTransactionsByWalletResponseMapper.class,
        ModelMapper.class})
@ExtendWith(SpringExtension.class)
class WalletControllerTest {
    @MockBean
    private CreateWalletUseCase createWalletUseCase;

    @MockBean
    private ObtainBalanceTransactionsByWalletUseCase obtainBalanceTransactionsByWalletUseCase;

    @Autowired
    private WalletController walletController;

    @Test
    void getWallet_ok() throws Exception {
        User user = generateUser();

        Wallet wallet = generateWallet(user);
        wallet.setBalance(BigDecimal.TEN);

        wallet.setTransactionList(generateTransactionsList(wallet));

        ObtainBalanceTransactionsByWalletRequest obtainBalanceTransactionsByWalletRequest = new ObtainBalanceTransactionsByWalletRequest();
        obtainBalanceTransactionsByWalletRequest.setWalletId(wallet.getWalletId());

        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(obtainBalanceTransactionsByWalletRequest.getWalletId()))
                .thenReturn(wallet);

        String content = (new ObjectMapper()).writeValueAsString(obtainBalanceTransactionsByWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"walletId\":"+wallet.getWalletId()+",\"balance\":"+wallet.getBalance()+",\"transactionResponseList\":[{\"transactionId\":0,\"destinationWallet\":1,"
                                + "\"originWallet\":0,\"transactionType\":\"DEPOSIT\",\"amount\":10,\"transactionDateTime\":[1980,1,1,0,0]}]}"));
    }


    @Test
    void getWallet_walletNotFound() throws Exception {
        ObtainBalanceTransactionsByWalletRequest obtainBalanceTransactionsByWalletRequest = new ObtainBalanceTransactionsByWalletRequest();
        obtainBalanceTransactionsByWalletRequest.setWalletId(1L);

        doThrow(new WalletNotFoundException(1L)).when(obtainBalanceTransactionsByWalletUseCase).obtainBalanceTransactionsByWallet(1L);

        String content = (new ObjectMapper()).writeValueAsString(obtainBalanceTransactionsByWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        assertThrows(NestedServletException.class, () -> MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder));

        try{
            MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder);
        } catch (NestedServletException e){
            assertEquals(WalletNotFoundException.class,e.getCause().getClass());
        }
    }

    @Test
    void getWallet_validation_ko() throws Exception {
        String content = (new ObjectMapper()).writeValueAsString(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void createWallet_ok() throws Exception {
        User user = generateUser();

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(user.getUserId());

        doReturn(1L).when(createWalletUseCase).createWallet(user.getUserId());

        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Wallet created")));
    }


    @Test
    void createWallet_walletNotFound() throws Exception {
        User user = generateUser();

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(user.getUserId());

        doThrow(new UserNotFoundException(1L)).when(createWalletUseCase).createWallet(user.getUserId());

        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        assertThrows(NestedServletException.class, () -> MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder));

        try{
            MockMvcBuilders.standaloneSetup(walletController)
                    .build()
                    .perform(requestBuilder);
        } catch (NestedServletException e){
            assertEquals(UserNotFoundException.class,e.getCause().getClass());
        }
    }

    @Test
    void createWallet_validation_ko() throws Exception {

        String content = (new ObjectMapper()).writeValueAsString(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}

