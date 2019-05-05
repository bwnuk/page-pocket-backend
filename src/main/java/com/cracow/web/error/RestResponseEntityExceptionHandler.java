package com.cracow.web.error;

import com.cracow.web.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse;
        HttpStatus status;
        if (ex instanceof ServiceException) {
            bodyOfResponse = ((ServiceException) ex).getMessage();
            status = ((ServiceException) ex).getStatus();
            logger.warn(bodyOfResponse);
        } else {
            bodyOfResponse = ex.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error(bodyOfResponse);
        }
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), status, request);
    }
}
