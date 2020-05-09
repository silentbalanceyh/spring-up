package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400QueryOpUnsupportException extends WebException {

    public _400QueryOpUnsupportException(final Class<?> clazz,
                                         final String op) {
        super(clazz, op);
    }

    @Override
    public int getCode() {
        return -60026;
    }
}
