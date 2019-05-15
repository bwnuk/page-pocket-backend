package com.cracow.error.common;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.UNAUTHORIZED;

public class UnauthorizedProblem extends AbstractThrowableProblem {
    private final static URI type = URI.create("unauthorized");

    public UnauthorizedProblem(String message) {
        super(
                type,
                "Unauthorized",
                UNAUTHORIZED,
                message,
                null,
                null,
                null
        );
    }
}
