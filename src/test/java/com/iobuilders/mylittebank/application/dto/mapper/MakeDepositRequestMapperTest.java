package com.iobuilders.mylittebank.application.dto.mapper;

import com.iobuilders.mylittebank.application.dto.request.MakeDepositRequest;
import com.iobuilders.mylittebank.domain.model.Transaction;
import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateDeposit;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateUser;
import static com.iobuilders.mylittebank.util.MyLittleBankTestUtils.generateWallet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {MakeDepositRequestMapper.class})
@ExtendWith(SpringExtension.class)
class MakeDepositRequestMapperTest {
    @Autowired
    private MakeDepositRequestMapper makeDepositRequestMapper;

    @MockBean
    private ModelMapper modelMapper;
    private MakeDepositRequest makeDepositRequest;

    @BeforeEach
    void init() {
        User user = generateUser();

        Wallet destinationWallet = generateWallet(user);

        Transaction transaction = generateDeposit(destinationWallet, BigDecimal.valueOf(1));

        makeDepositRequest = new MakeDepositRequest();
        makeDepositRequest.setAmount(transaction.getAmount());
        makeDepositRequest.setDestinationWalletId(destinationWallet.getWalletId());

        when(modelMapper.map(makeDepositRequest,Transaction.class)).thenReturn(transaction);
        when(modelMapper.map(null,Transaction.class)).thenThrow(NullPointerException.class);
    }

    @Test
    void fromMakeDepositRequestToTransaction_result_ok() {

        Transaction transactionResult = makeDepositRequestMapper.toTransaction(makeDepositRequest);

        assertEquals(makeDepositRequest.getDestinationWalletId(), transactionResult.getDestinationWallet().getWalletId());
        assertEquals(makeDepositRequest.getAmount(), transactionResult.getAmount());

        verify(modelMapper).map(makeDepositRequest, Transaction.class);
    }

    @Test
    void fromMakeDepositRequestToTransaction_verify_map_call() {
        makeDepositRequestMapper.toTransaction(makeDepositRequest);
        verify(modelMapper).map(makeDepositRequest, Transaction.class);
    }

    @Test
    void fromMakeDepositRequestToTransaction_result_ko() {
        assertThrows(NullPointerException.class, () -> makeDepositRequestMapper.toTransaction(null));
    }

}

