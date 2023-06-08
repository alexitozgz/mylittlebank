package com.iobuilders.mylittebank.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

@ContextConfiguration(classes = {CreateWalletService.class})
@ExtendWith(SpringExtension.class)
class CreateWalletServiceTest {
    @MockBean
    private CreateWalletPort createWalletPort;

    @Autowired
    private CreateWalletService createWalletService;

    @MockBean
    private ObtainUserPort obtainUserPort;

    /**
     * Method under test: {@link CreateWalletService#CreateWalletService(CreateWalletPort, ObtainUserPort)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CreateWalletService.createWalletPort
        //     CreateWalletService.obtainUserPort

        new CreateWalletService(mock(CreateWalletPort.class), mock(ObtainUserPort.class));

    }

    /**
     * Method under test: {@link CreateWalletService#createWallet(Long)}
     */
    @Test
    void testCreateWallet() throws UserNotFoundException {
        doNothing().when(createWalletPort).createWallet(Mockito.<Long>any());
        doNothing().when(obtainUserPort).obtainUser(Mockito.<Long>any());
        createWalletService.createWallet(1L);
        verify(createWalletPort).createWallet(Mockito.<Long>any());
        verify(obtainUserPort).obtainUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CreateWalletService#createWallet(Long)}
     */
    @Test
    void testCreateWallet2() throws UserNotFoundException {
        doNothing().when(createWalletPort).createWallet(Mockito.<Long>any());
        doThrow(new UserNotFoundException("An error occurred")).when(obtainUserPort).obtainUser(Mockito.<Long>any());
        assertThrows(UserNotFoundException.class, () -> createWalletService.createWallet(1L));
        verify(obtainUserPort).obtainUser(Mockito.<Long>any());
    }
}

