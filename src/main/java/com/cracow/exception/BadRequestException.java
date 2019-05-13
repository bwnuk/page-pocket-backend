package com.cracow.exception;

import org.springframework.http.HttpStatus;

//400 Bad Request
//        This response means that server could not understand the request due to invalid syntax.

public class BadRequestException extends ServiceException{

    public BadRequestException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST, errorCode);
    }
}