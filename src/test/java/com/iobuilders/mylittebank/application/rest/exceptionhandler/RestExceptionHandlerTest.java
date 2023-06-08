package com.iobuilders.mylittebank.application.rest.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.exceptions.MyLittleBankGenericException;
import com.iobuilders.mylittebank.domain.exceptions.UserNotFoundException;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

class RestExceptionHandlerTest {
    /**
     * Method under test: {@link RestExceptionHandler#handleMyLittleBankApplicationGenericException(MyLittleBankGenericException)}
     */
    @Test
    void testHandleMyLittleBankApplicationGenericException() {
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        ResponseEntity<Object> actualHandleMyLittleBankApplicationGenericExceptionResult = restExceptionHandler
                .handleMyLittleBankApplicationGenericException(new UserNotFoundException(1L));
        assertTrue(actualHandleMyLittleBankApplicationGenericExceptionResult.hasBody());
        assertTrue(actualHandleMyLittleBankApplicationGenericExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMyLittleBankApplicationGenericExceptionResult.getStatusCode());
        assertEquals("The user provided has not been found --> 1",
                ((MyLittleBankResponse) actualHandleMyLittleBankApplicationGenericExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST,
                ((MyLittleBankResponse) actualHandleMyLittleBankApplicationGenericExceptionResult.getBody()).getStatus());
    }

    /**
     * Method under test: {@link RestExceptionHandler#handleMyLittleBankApplicationGenericException(MyLittleBankGenericException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleMyLittleBankApplicationGenericException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.iobuilders.mylittebank.application.rest.exceptionhandler.RestExceptionHandler.handleMyLittleBankApplicationGenericException(RestExceptionHandler.java:21)
        //   See https://diff.blue/R013 to resolve this issue.

        (new RestExceptionHandler()).handleMyLittleBankApplicationGenericException(null);
    }

    /**
     * Method under test: {@link RestExceptionHandler#handleException(MethodArgumentNotValidException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Constructor must not be null
        //   See https://diff.blue/R013 to resolve this issue.

        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        MethodParameter parameter = new MethodParameter((Constructor<?>) null, 1);

        restExceptionHandler
                .handleException(new MethodArgumentNotValidException(parameter, new BindException("Target", "Object Name")));
    }

    /**
     * Method under test: {@link RestExceptionHandler#handleException(MethodArgumentNotValidException)}
     */
    @Test
    void testHandleException2() {
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler();
        ResponseEntity<Object> actualHandleExceptionResult = restExceptionHandler
                .handleException(new MethodArgumentNotValidException(null, new BindException("Target", "Object Name")));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
        assertEquals("", ((MyLittleBankResponse) actualHandleExceptionResult.getBody()).getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ((MyLittleBankResponse) actualHandleExceptionResult.getBody()).getStatus());
    }

    /**
     * Method under test: {@link RestExceptionHandler#handleException(MethodArgumentNotValidException)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.iobuilders.mylittebank.application.rest.exceptionhandler.RestExceptionHandler.handleException(RestExceptionHandler.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        (new RestExceptionHandler()).handleException(null);
    }
}

