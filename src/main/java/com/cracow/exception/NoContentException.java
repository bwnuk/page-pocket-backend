package com.cracow.exception;

import org.springframework.http.HttpStatus;

//        The 204 (No Content) status code indicates that the server has
//        successfully fulfilled the request and that there is no additional
//        content to send in the response payload body.  Metadata in the
//        response header fields refer to the target resource and its selected
//        representation after the requested action was applied.

public class NoContentException extends ServiceException {

    public NoContentException(String message, ServiceErrorCode errorCode) {
        super(message, HttpStatus.NO_CONTENT, errorCode);
    }
}