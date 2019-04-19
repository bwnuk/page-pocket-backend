package com.cracow.web.exception;

import org.springframework.http.HttpStatus;

//        The 201 (Created) status code indicates that the request has been
//        fulfilled and has resulted in one or more new resources being
//        created.  The primary resource created by the request is identified
//        by either a Location header field in the response or, if no Location
//        field is received, by the effective request URI.

public class CreatedException extends ServiceException {

    public CreatedException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.CREATED, errorCode);
    }
}