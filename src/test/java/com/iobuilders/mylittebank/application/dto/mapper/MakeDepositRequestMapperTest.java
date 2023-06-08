package com.iobuilders.mylittebank.application.dto.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MakeDepositRequestMapper.class})
@ExtendWith(SpringExtension.class)
class MakeDepositRequestMapperTest {
    @Autowired
    private MakeDepositRequestMapper makeDepositRequestMapper;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link MakeDepositRequestMapper#toTransaction(MakeDepositRequest)}
     */
    @Test
    void testToTransaction() {
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
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Transaction>>any())).thenReturn(transaction);

        MakeDepositRequest makeDepositRequest = new MakeDepositRequest();
        makeDepositRequest.setAmount(BigDecimal.valueOf(1L));
        makeDepositRequest.setDestinationWalletId(1L);
        Transaction actualToTransactionResult = makeDepositRequestMapper.toTransaction(makeDepositRequest);
        assertSame(transaction, actualToTransactionResult);
        assertEquals("1", actualToTransactionResult.getAmount().toString());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Transaction>>any());
    }
}

