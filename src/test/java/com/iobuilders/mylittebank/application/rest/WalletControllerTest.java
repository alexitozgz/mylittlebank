package com.iobuilders.mylittebank.application.rest;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iobuilders.mylittebank.application.dto.mapper.BalanceTransactionsByWalletResponseMapper;
import com.iobuilders.mylittebank.application.dto.request.CreateWalletRequest;
import com.iobuilders.mylittebank.application.dto.request.ObtainBalanceTransactionsByWalletRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.inbound.CreateWalletUseCase;
import com.iobuilders.mylittebank.domain.ports.inbound.ObtainBalanceTransactionsByWalletUseCase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

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

    /**
     * Method under test: {@link WalletController#createWallet(CreateWalletRequest)}
     */
    @Test
    void testCreateWallet() throws Exception {
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
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[]}"));
    }

    /**
     * Method under test: {@link WalletController#createWallet(CreateWalletRequest)}
     */
    @Test
    void testCreateWallet2() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
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
        transaction.setTransactionType("?");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user3);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[{\"transactionId\":1,\"destinationWallet\":1,"
                                        + "\"originWallet\":1,\"transactionType\":\"?\",\"amount\":-1,\"transactionDateTime\":[1970,1,1,0,0]}]}"));
    }

    /**
     * Method under test: {@link WalletController#createWallet(CreateWalletRequest)}
     */
    @Test
    void testCreateWallet3() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
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
        transaction.setTransactionType("?");

        User user3 = new User();
        user3.setEmail("john.smith@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("8605550118");
        user3.setUserId(2L);
        user3.setWallet(new HashSet<>());

        Wallet destinationWallet2 = new Wallet();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setTransactionList(new ArrayList<>());
        destinationWallet2.setUser(user3);
        destinationWallet2.setWalletId(2L);

        User user4 = new User();
        user4.setEmail("john.smith@example.org");
        user4.setName("Name");
        user4.setPhoneNumber("8605550118");
        user4.setUserId(2L);
        user4.setWallet(new HashSet<>());

        Wallet originWallet2 = new Wallet();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setTransactionList(new ArrayList<>());
        originWallet2.setUser(user4);
        originWallet2.setWalletId(2L);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(BigDecimal.valueOf(1L));
        transaction2.setDestinationWallet(destinationWallet2);
        transaction2.setOriginWallet(originWallet2);
        transaction2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setTransactionId(2L);
        transaction2.setTransactionType("Transaction Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction2);
        transactionList.add(transaction);

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setName("Name");
        user5.setPhoneNumber("6625550144");
        user5.setUserId(1L);
        user5.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user5);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[{\"transactionId\":2,\"destinationWallet\":2,"
                                        + "\"originWallet\":2,\"transactionType\":\"Transaction Type\",\"amount\":1,\"transactionDateTime\":[1970,1,1,0,0"
                                        + "]},{\"transactionId\":1,\"destinationWallet\":1,\"originWallet\":1,\"transactionType\":\"?\",\"amount\":-1,"
                                        + "\"transactionDateTime\":[1970,1,1,0,0]}]}"));
    }

    /**
     * Method under test: {@link WalletController#createWallet(CreateWalletRequest)}
     */
    @Test
    void testCreateWallet4() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(null);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("?");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user3);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);

        CreateWalletRequest createWalletRequest = new CreateWalletRequest();
        createWalletRequest.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(createWalletRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    /**
     * Method under test: {@link WalletController#getWallet(ObtainBalanceTransactionsByWalletRequest)}
     */
    @Test
    void testGetWallet() throws Exception {
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
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ObtainBalanceTransactionsByWalletRequest()));
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[]}"));
    }

    /**
     * Method under test: {@link WalletController#getWallet(ObtainBalanceTransactionsByWalletRequest)}
     */
    @Test
    void testGetWallet2() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
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
        transaction.setTransactionType("?");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user3);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ObtainBalanceTransactionsByWalletRequest()));
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[{\"transactionId\":1,\"destinationWallet\":1,"
                                        + "\"originWallet\":1,\"transactionType\":\"?\",\"amount\":-1,\"transactionDateTime\":[1970,1,1,0,0]}]}"));
    }

    /**
     * Method under test: {@link WalletController#getWallet(ObtainBalanceTransactionsByWalletRequest)}
     */
    @Test
    void testGetWallet3() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
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
        transaction.setTransactionType("?");

        User user3 = new User();
        user3.setEmail("john.smith@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("8605550118");
        user3.setUserId(2L);
        user3.setWallet(new HashSet<>());

        Wallet destinationWallet2 = new Wallet();
        destinationWallet2.setBalance(BigDecimal.valueOf(1L));
        destinationWallet2.setTransactionList(new ArrayList<>());
        destinationWallet2.setUser(user3);
        destinationWallet2.setWalletId(2L);

        User user4 = new User();
        user4.setEmail("john.smith@example.org");
        user4.setName("Name");
        user4.setPhoneNumber("8605550118");
        user4.setUserId(2L);
        user4.setWallet(new HashSet<>());

        Wallet originWallet2 = new Wallet();
        originWallet2.setBalance(BigDecimal.valueOf(1L));
        originWallet2.setTransactionList(new ArrayList<>());
        originWallet2.setUser(user4);
        originWallet2.setWalletId(2L);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(BigDecimal.valueOf(1L));
        transaction2.setDestinationWallet(destinationWallet2);
        transaction2.setOriginWallet(originWallet2);
        transaction2.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setTransactionId(2L);
        transaction2.setTransactionType("Transaction Type");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction2);
        transactionList.add(transaction);

        User user5 = new User();
        user5.setEmail("jane.doe@example.org");
        user5.setName("Name");
        user5.setPhoneNumber("6625550144");
        user5.setUserId(1L);
        user5.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user5);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ObtainBalanceTransactionsByWalletRequest()));
        MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"walletId\":1,\"balance\":1,\"transactionResponseList\":[{\"transactionId\":2,\"destinationWallet\":2,"
                                        + "\"originWallet\":2,\"transactionType\":\"Transaction Type\",\"amount\":1,\"transactionDateTime\":[1970,1,1,0,0"
                                        + "]},{\"transactionId\":1,\"destinationWallet\":1,\"originWallet\":1,\"transactionType\":\"?\",\"amount\":-1,"
                                        + "\"transactionDateTime\":[1970,1,1,0,0]}]}"));
    }

    /**
     * Method under test: {@link WalletController#getWallet(ObtainBalanceTransactionsByWalletRequest)}
     */
    @Test
    void testGetWallet4() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("?");
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
        user2.setName("?");
        user2.setPhoneNumber("6625550144");
        user2.setUserId(1L);
        user2.setWallet(new HashSet<>());

        Wallet originWallet = new Wallet();
        originWallet.setBalance(BigDecimal.valueOf(1L));
        originWallet.setTransactionList(new ArrayList<>());
        originWallet.setUser(user2);
        originWallet.setWalletId(1L);

        Transaction transaction = new Transaction();
        transaction.setAmount(null);
        transaction.setDestinationWallet(destinationWallet);
        transaction.setOriginWallet(originWallet);
        transaction.setTransactionDateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setTransactionId(1L);
        transaction.setTransactionType("?");

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPhoneNumber("6625550144");
        user3.setUserId(1L);
        user3.setWallet(new HashSet<>());

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1L));
        wallet.setTransactionList(transactionList);
        wallet.setUser(user3);
        wallet.setWalletId(1L);
        when(obtainBalanceTransactionsByWalletUseCase.obtainBalanceTransactionsByWallet(Mockito.<Long>any()))
                .thenReturn(wallet);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/wallets")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ObtainBalanceTransactionsByWalletRequest()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(walletController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }
}

