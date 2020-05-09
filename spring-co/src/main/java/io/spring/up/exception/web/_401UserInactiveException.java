package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _401UserInactiveException extends WebException {

    public _401UserInactiveException(final Class<?> clazz,
                                     final String user) {
        super(clazz, user);
    }

    @Override
    public int getCode() {
        return -20012;
    }
}
