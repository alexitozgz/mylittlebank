package com.iobuilders.mylittebank.application.rest.exceptionhandler;

import com.iobuilders.mylittebank.application.dto.response.MyLittleBankResponse;
import com.iobuilders.mylittebank.domain.exceptions.MyLittleBankGenericException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(MyLittleBankGenericException.class)
    protected ResponseEntity<Object> handleMyLittleBankApplicationGenericException(MyLittleBankGenericException myLittleBankGenericException) {
        MyLittleBankResponse errorMyLittleBankResponse = new MyLittleBankResponse(HttpStatus.BAD_REQUEST, myLittleBankGenericException.getMessage());
        return buildResponseEntity(errorMyLittleBankResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String errorMessage = error.getField() + ": " + error.getDefaultMessage();
            stringBuilder.append(errorMessage);
        }
        MyLittleBankResponse errorMyLittleBankResponse = new MyLittleBankResponse(HttpStatus.BAD_REQUEST, stringBuilder.toString());

        return buildResponseEntity(errorMyLittleBankResponse);
    }


    private ResponseEntity<Object> buildResponseEntity(MyLittleBankResponse myLittleBankResponse) {
        return new ResponseEntity<>(myLittleBankResponse, myLittleBankResponse.getStatus());
    }

}