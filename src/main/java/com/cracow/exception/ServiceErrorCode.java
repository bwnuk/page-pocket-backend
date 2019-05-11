package com.cracow.exception;


public enum ServiceErrorCode {
    NOT_FOUND(1),
    ALREADY_EXIST(2),
    NOT_MODIFY(3),
    CONNECTION_FAILED(4),
    UNAUTHORIZED_LOGIN_LOGOUT_FAILED(5);

    private final int code;

    ServiceErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}