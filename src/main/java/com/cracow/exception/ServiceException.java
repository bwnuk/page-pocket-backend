package com.cracow.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private HttpStatus status;
    private ServiceErrorCode errorCode;

    public ServiceException(String message, HttpStatus status, ServiceErrorCode errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return String.format("Exception message: {%s}, http status code: {%s}, error code: {%s}",
                super.getMessage(),
                status.toString(), errorCode.name());
    }
}