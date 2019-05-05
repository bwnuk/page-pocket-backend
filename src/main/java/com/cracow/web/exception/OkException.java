package com.cracow.web.exception;

import org.springframework.http.HttpStatus;

//         The 200 (OK) status code indicates that the request has succeeded.
//         The payload sent in a 200 response depends on the request method.

public class OkException extends ServiceException {

    public OkException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.OK, errorCode);
    }
}