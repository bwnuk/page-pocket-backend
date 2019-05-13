package com.cracow.error.common;

import com.google.common.collect.ImmutableMap;
import org.springframework.util.StringUtils;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static java.lang.String.format;
import static org.zalando.problem.Status.NOT_FOUND;

public class NotFoundProblem extends AbstractThrowableProblem {

    private final static URI type = URI.create("not-found");

    public NotFoundProblem(String resourceName, String resourceFieldName, String resourceId) {
        super(
                type,
                format("%s Not Found", StringUtils.capitalize(resourceName)),
                NOT_FOUND,
                format("Resource %s with %s %s not found", resourceName, resourceFieldName, resourceId),
                null,
                null,
                ImmutableMap.of(resourceFieldName, resourceId)
        );
    }
}