package com.cracow.error.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

public class InternalServerErrorProblem extends AbstractThrowableProblem {

    private final static URI type = URI.create("interval-server-error");

    public InternalServerErrorProblem(String message) {
        super(
                type,
                "Internal Server Error",
                INTERNAL_SERVER_ERROR,
                message,
                null,
                null,
                null
        );
    }
}