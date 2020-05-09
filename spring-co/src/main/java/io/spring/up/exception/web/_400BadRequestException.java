package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400BadRequestException extends WebException {

    public _400BadRequestException(final Class<?> clazz,
                                   final String details) {
        super(clazz, details);
    }

    @Override
    public int getCode() {
        return -20007;
    }
}