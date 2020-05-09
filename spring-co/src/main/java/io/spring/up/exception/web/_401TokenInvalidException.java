package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _401TokenInvalidException extends WebException {

    public _401TokenInvalidException(final Class<?> clazz,
                                     final String endpoint,
                                     final Throwable ex) {
        super(clazz, endpoint, ex.getMessage());
    }

    @Override
    public int getCode() {
        return -20021;
    }
}