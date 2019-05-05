package com.cracow.web.exception;

import org.springframework.http.HttpStatus;

//        The 404 (Not Found) status code indicates that the origin server did
//        not find a current representation for the target resource or is not
//        willing to disclose that one exists.  A 404 status code does not
//        indicate whether this lack of representation is temporary or
//        permanent; the 410 (Gone) status code is preferred over 404 if the
//        origin server knows, presumably through some configurable means, that
//        the condition is likely to be permanent.

public class NotFoundException extends ServiceException {

    public NotFoundException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.NOT_FOUND, errorCode);
    }
}