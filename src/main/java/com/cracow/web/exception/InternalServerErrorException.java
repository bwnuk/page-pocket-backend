package com.cracow.web.exception;

import org.springframework.http.HttpStatus;

//            The 500 (Internal Server Error) status code indicates that the server
//            encountered an unexpected condition that prevented it from fulfilling
//            the request.

public class InternalServerErrorException extends ServiceException {
    public InternalServerErrorException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }
}