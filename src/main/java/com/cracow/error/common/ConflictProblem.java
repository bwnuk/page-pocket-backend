package com.cracow.error.common;

import com.google.common.collect.ImmutableMap;
import org.springframework.util.StringUtils;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static java.lang.String.format;
import static org.zalando.problem.Status.CONFLICT;

public class ConflictProblem extends AbstractThrowableProblem {

    private final static URI type = URI.create("conflict");

    public ConflictProblem(String resourceName, String resourceFieldName, String resourceId) {
        super(
                type,
                format("%s Conflict", StringUtils.capitalize(resourceName)),
                CONFLICT,
                format("Resource %s with %s %s already exists", resourceName, resourceFieldName, resourceId),
                null,
                null,
                ImmutableMap.of(resourceFieldName, resourceId)
        );
    }
}