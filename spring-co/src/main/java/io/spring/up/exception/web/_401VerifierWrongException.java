package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _401VerifierWrongException extends WebException {

    public _401VerifierWrongException(final Class<?> clazz,
                                      final String endpoint,
                                      final Throwable ex) {
        super(clazz, endpoint, ex.getMessage());
    }

    @Override
    public int getCode() {
        return -20020;
    }
}
