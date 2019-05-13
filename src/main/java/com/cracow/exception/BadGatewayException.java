package com.cracow.exception;

import org.springframework.http.HttpStatus;

//502 Bad Gateway
//        This error response means that the server,
//        while working as a gateway to get a response needed to handle the request, got an invalid response.

public class BadGatewayException extends ServiceException {
    public BadGatewayException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }
}