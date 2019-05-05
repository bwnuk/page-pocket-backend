package com.cracow.web.exception;

import org.springframework.http.HttpStatus;

//        The 409 (Conflict) status code indicates that the request could not
//        be completed due to a conflict with the current state of the target
//        resource.  This code is used in situations where the user might be
//        able to resolve the conflict and resubmit the request.  The server
//        SHOULD generate a payload that includes enough information for a user
//        to recognize the source of the conflict.

public class ConflictException extends ServiceException {

    public ConflictException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.CONFLICT, errorCode);
    }
}