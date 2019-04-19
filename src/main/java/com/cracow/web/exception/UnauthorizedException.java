package com.cracow.web.exception;


import org.springframework.http.HttpStatus;

//        The 401 (Unauthorized) status code indicates that the request has not
//        been applied because it lacks valid authentication credentials for
//        the target resource.  The server generating a 401 response MUST send
//        a WWW-Authenticate header field (Section 4.1) containing at least one
//        challenge applicable to the target resource.

public class UnauthorizedException extends ServiceException {

    public UnauthorizedException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.UNAUTHORIZED, errorCode);
    }
}