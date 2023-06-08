package com.iobuilders.mylittebank.domain.service;

import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import com.iobuilders.mylittebank.domain.ports.outbound.CreateWalletPort;
import com.iobuilders.mylittebank.domain.ports.outbound.ObtainUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = {CreateWalletService.class})
@ExtendWith(SpringExtension.class)
class CreateWalletServiceTest {
    public static final Long USER_ID = 1L;
    public static final Long WALLET_ID = 20L;

    @MockBean
    private CreateWalletPort createWalletPort;

    @Autowired
    private CreateWalletService createWalletService;

    @MockBean
    private ObtainUserPort obtainUserPort;

    @Test
    void createWallet_ok() throws UserNotFoundException {
        doNothing().when(obtainUserPort).existsUser(USER_ID);
        doReturn(WALLET_ID).when(createWalletPort).createWallet(USER_ID);

        assertEquals(WALLET_ID,createWalletService.createWallet(USER_ID));
    }
    @Test
    void createWallet_verify_port_calls() throws UserNotFoundException {
        doNothing().when(obtainUserPort).existsUser(USER_ID);
        doReturn(WALLET_ID).when(createWalletPort).createWallet(USER_ID);

        createWalletService.createWallet(USER_ID);

        verify(createWalletPort).createWallet(USER_ID);
        verify(obtainUserPort).existsUser(USER_ID);
    }

    /**
     * Method under test: {@link CreateWalletService#createWallet(Long)}
     */
    @Test
    void createWallet_userNotFoundException() throws UserNotFoundException {
        doThrow(new UserNotFoundException(USER_ID)).when(obtainUserPort).existsUser(USER_ID);
        assertThrows(UserNotFoundException.class, () -> createWalletService.createWallet(USER_ID));
        verify(obtainUserPort).existsUser(Mockito.<Long>any());
    }
}

