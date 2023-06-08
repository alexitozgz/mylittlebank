package com.iobuilders.mylittebank.infrastructure.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.domain.exceptions.NotEnoughMoneyException;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.exceptions.WalletNotFoundException;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeDepositPort;
import com.iobuilders.mylittebank.domain.ports.outbound.MakeTransferPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainTransactionsByWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.RegisterUserPort;
import com.iobuilders.mylittebank.domain.ports.outbound.UpdateWalletPort;
import com.iobuilders.mylittebank.domain.service.MakeDepositService;
import com.iobuilders.mylittebank.domain.service.MakeTransferService;
import com.iobuilders.mylittebank.domain.service.RegisterUserService;
import com.iobuilders.mylittebank.infrastructure.mapper.TransactionMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.UserMapper;
import com.iobuilders.mylittebank.infrastructure.mapper.WalletMapper;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.TransactionRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.UserRepository;
import com.iobuilders.mylittebank.infrastructure.persistence.repository.WalletRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.modelmapper.internal.InheritingConfiguration;
import org.modelmapper.internal.TypeResolvingList;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.NameTokenizer;
import org.modelmapper.spi.ValueReader;
import org.modelmapper.spi.ValueWriter;

class BeanConfigurationTest {
    /**
     * Method under test: {@link BeanConfiguration#modelMapper()}
     */
    @Test
    void testModelMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ModelMapper actualModelMapperResult = (new BeanConfiguration()).modelMapper();
        assertTrue(actualModelMapperResult.getTypeMaps().isEmpty());
        Configuration configuration = actualModelMapperResult.getConfiguration();
        assertEquals(11, configuration.getConverters().size());
        assertNull(configuration.getPropertyCondition());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertTrue(valueWriters instanceof TypeResolvingList);
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertTrue(valueReaders instanceof TypeResolvingList);
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
    }

    /**
     * Method under test: {@link BeanConfiguration#userMapper()}
     */
    @Test
    void testUserMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     UserMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.userMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#walletMapper()}
     */
    @Test
    void testWalletMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     WalletMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.walletMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#transactionMapper()}
     */
    @Test
    void testTransactionMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TransactionMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.transactionMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#balanceTransactionsByWalletResponseMapper()}
     */
    @Test
    void testBalanceTransactionsByWalletResponseMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BalanceTransactionsByWalletResponseMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.balanceTransactionsByWalletResponseMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#makeTransferRequestMapper()}
     */
    @Test
    void testMakeTransferRequestMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.makeTransferRequestMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#makeDepositRequestMapper()}
     */
    @Test
    void testMakeDepositRequestMapper() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MakeDepositRequestMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        beanConfiguration.makeDepositRequestMapper();
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#userPersistenceAdapter(UserRepository, UserMapper)}
     */
    @Test
    void testUserPersistenceAdapter() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     UserPersistenceAdapter.userMapper
        //     UserPersistenceAdapter.userRepository
        //     UserMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        UserRepository userRepository = mock(UserRepository.class);
        beanConfiguration.userPersistenceAdapter(userRepository, new UserMapper());
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#walletPersistenceAdapter(WalletRepository, WalletMapper)}
     */
    @Test
    void testWalletPersistenceAdapter() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     WalletPersistenceAdapter.walletMapper
        //     WalletPersistenceAdapter.walletRepository
        //     WalletMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        WalletRepository userRepository = mock(WalletRepository.class);
        beanConfiguration.walletPersistenceAdapter(userRepository, new WalletMapper());
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#depositPersistenceAdapter(TransactionRepository, TransactionMapper, WalletMapper)}
     */
    @Test
    void testDepositPersistenceAdapter() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TransactionPersistenceAdapter.transactionMapper
        //     TransactionPersistenceAdapter.transactionRepository
        //     TransactionPersistenceAdapter.walletMapper
        //     TransactionMapper.modelMapper
        //     WalletMapper.modelMapper

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        TransactionMapper transactionMapper = new TransactionMapper();
        beanConfiguration.depositPersistenceAdapter(transactionRepository, transactionMapper, new WalletMapper());
        ModelMapper modelMapperResult = beanConfiguration.modelMapper();
        Collection<TypeMap<?, ?>> typeMaps = modelMapperResult.getTypeMaps();
        assertTrue(typeMaps.isEmpty());
        Configuration configuration = modelMapperResult.getConfiguration();
        List<ConditionalConverter<?, ?>> converters = configuration.getConverters();
        assertEquals(11, converters.size());
        assertFalse(configuration.isDeepCopyEnabled());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getFieldAccessLevel());
        NameTokenizer expectedSourceNameTokenizer = configuration.getDestinationNameTokenizer();
        assertSame(expectedSourceNameTokenizer, configuration.getSourceNameTokenizer());
        assertTrue(configuration.isCollectionsMergeEnabled());
        List<ValueWriter<?>> valueWriters = configuration.getValueWriters();
        assertEquals(1, valueWriters.size());
        List<ValueReader<?>> valueReaders = configuration.getValueReaders();
        assertEquals(1, valueReaders.size());
        assertEquals(Configuration.AccessLevel.PUBLIC, configuration.getMethodAccessLevel());
        assertSame(typeMaps, ((InheritingConfiguration) configuration).typeMapStore.get());
        assertSame(valueReaders, ((InheritingConfiguration) configuration).valueAccessStore.getValueReaders());
        assertSame(valueWriters, ((InheritingConfiguration) configuration).valueMutateStore.getValueWriters());
        assertSame(converters, ((InheritingConfiguration) configuration).converterStore.getConverters());
        assertFalse(valueWriters.get(0).isResolveMembersSupport());
    }

    /**
     * Method under test: {@link BeanConfiguration#registerUserService(RegisterUserPort)}
     */
    @Test
    void testRegisterUserService() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RegisterUserService.registerUserPort

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        RegisterUserPort registerUserPort = mock(RegisterUserPort.class);
        doNothing().when(registerUserPort).registerUser(Mockito.<User>any());
        RegisterUserService actualRegisterUserServiceResult = beanConfiguration.registerUserService(registerUserPort);
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPhoneNumber("6625550144");
        user.setUserId(1L);
        user.setWallet(new HashSet<>());
        actualRegisterUserServiceResult.registerUser(user);
        verify(registerUserPort).registerUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link BeanConfiguration#createWalletService(CreateWalletPort, ObtainUserPort)}
     */
    @Test
    void testCreateWalletService() throws UserNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreateWalletService.createWalletPort
        //     CreateWalletService.obtainUserPort

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        CreateWalletPort createWalletPort = mock(CreateWalletPort.class);
        doNothing().when(createWalletPort).createWallet(Mockito.<Long>any());
        ObtainUserPort obtainUserPort = mock(ObtainUserPort.class);
        doNothing().when(obtainUserPort).obtainUser(Mockito.<Long>any());
        beanConfiguration.createWalletService(createWalletPort, obtainUserPort).createWallet(1L);
        verify(createWalletPort).createWallet(Mockito.<Long>any());
        verify(obtainUserPort).obtainUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BeanConfiguration#makeDepositService(MakeDepositPort, ObtainWalletPort, UpdateWalletPort)}
     */
    @Test
    void testMakeDepositService() throws WalletNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MakeDepositService.makeDepositPort
        //     MakeDepositService.obtainWalletPort
        //     MakeDepositService.updateWalletPort

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        MakeDepositPort makeDepositPort = mock(MakeDepositPort.class);
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
        ObtainWalletPort obtainWalletPort = mock(ObtainWalletPort.class);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        UpdateWalletPort updateWalletPort = mock(UpdateWalletPort.class);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());
        MakeDepositService actualMakeDepositServiceResult = beanConfiguration.makeDepositService(makeDepositPort,
                obtainWalletPort, updateWalletPort);
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
        actualMakeDepositServiceResult.makeDeposit(transaction);
        verify(makeDepositPort).createDeposit(Mockito.<Transaction>any());
        verify(obtainWalletPort).obtainWalletPort(Mockito.<Long>any());
        verify(updateWalletPort).updateWallet(Mockito.<Wallet>any());
        assertEquals("DEPOSIT", transaction.getTransactionType());
    }

    /**
     * Method under test: {@link BeanConfiguration#makeTransferService(MakeTransferPort, ObtainWalletPort, UpdateWalletPort)}
     */
    @Test
    void testMakeTransferService() throws NotEnoughMoneyException, WalletNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     MakeTransferService.makeTransferPort
        //     MakeTransferService.obtainWalletPort
        //     MakeTransferService.updateWalletPort

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        MakeTransferPort makeTransferPort = mock(MakeTransferPort.class);
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
        ObtainWalletPort obtainWalletPort = mock(ObtainWalletPort.class);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        UpdateWalletPort updateWalletPort = mock(UpdateWalletPort.class);
        doNothing().when(updateWalletPort).updateWallet(Mockito.<Wallet>any());
        MakeTransferService actualMakeTransferServiceResult = beanConfiguration.makeTransferService(makeTransferPort,
                obtainWalletPort, updateWalletPort);
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
        try {
            actualMakeTransferServiceResult.makeTransfer(transaction);
        } catch (WalletNotFoundException e) {
            throw new RuntimeException(e);
        }
        verify(makeTransferPort).createTransfer(Mockito.<Transaction>any());
        verify(obtainWalletPort, atLeast(1)).obtainWalletPort(Mockito.<Long>any());
        verify(updateWalletPort, atLeast(1)).updateWallet(Mockito.<Wallet>any());
        assertEquals("TRANSFER", transaction.getTransactionType());
    }

    /**
     * Method under test: {@link BeanConfiguration#obtainBalanceTransactionsByWalletUseCaseService(ObtainTransactionsByWalletPort, ObtainWalletPort)}
     */
    @Test
    void testObtainBalanceTransactionsByWalletUseCaseService() throws WalletNotFoundException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ObtainBalanceTransactionsByWalletUseCaseService.obtainTransactionsByWalletPort
        //     ObtainBalanceTransactionsByWalletUseCaseService.obtainWalletPort

        BeanConfiguration beanConfiguration = new BeanConfiguration();
        ObtainTransactionsByWalletPort obtainTransactionsByWalletPort = mock(ObtainTransactionsByWalletPort.class);
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
        ObtainWalletPort obtainWalletPort = mock(ObtainWalletPort.class);
        when(obtainWalletPort.obtainWalletPort(Mockito.<Long>any())).thenReturn(wallet);
        Wallet actualObtainBalanceTransactionsByWalletResult = beanConfiguration
                .obtainBalanceTransactionsByWalletUseCaseService(obtainTransactionsByWalletPort, obtainWalletPort)
                .obtainBalanceTransactionsByWallet(1L);
        verify(obtainTransactionsByWalletPort).obtainTransactionsByWalletPort(Mockito.<Wallet>any());
        verify(obtainWalletPort).obtainWalletPort(Mockito.<Long>any());
        assertSame(wallet, actualObtainBalanceTransactionsByWalletResult);
        assertEquals(transactionList, actualObtainBalanceTransactionsByWalletResult.getTransactionList());
        assertEquals("1", actualObtainBalanceTransactionsByWalletResult.getBalance().toString());
    }
}

