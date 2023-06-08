package com.iobuilders.mylittebank.application.rest.exceptionhandler;

import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



class RestExceptionHandlerTest {
    @Test
    void handleMyLittleBankApplicationGenericException_result_ok() {
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        ResponseEntity<Object> exceptionResult = restExceptionHandler.handleMyLittleBankApplicationGenericException(new UserNotFoundException(1L));
        assertTrue(exceptionResult.hasBody());
        assertTrue(exceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, exceptionResult.getStatusCode());
        assertEquals("The user provided has not been found --> 1", ((MyLittleBankResponse) exceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ((MyLittleBankResponse) exceptionResult.getBody()).getStatus());
    }

    @Test
    void handleMethodArgumentNotValidException_result_ok() {
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        ResponseEntity<Object> exceptionResult = restExceptionHandler.handleException(new MethodArgumentNotValidException(null, new BindException("Target", "Object Name")));
        assertTrue(exceptionResult.hasBody());
        assertTrue(exceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, exceptionResult.getStatusCode());
        assertEquals("", ((MyLittleBankResponse) exceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ((MyLittleBankResponse) exceptionResult.getBody()).getStatus());
    }
}

