package com.cracow.exception;

import org.springframework.http.HttpStatus;

//403 Forbidden
//        The client does not have access rights to the content, i.e. they are unauthorized,
//        so server is rejecting to give proper response.
//        Unlike 401, the client's identity is known to the server.

public class ForbiddenException extends ServiceException{

    public ForbiddenException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.FORBIDDEN, errorCode);
    }
}