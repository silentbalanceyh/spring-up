package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400SerializationException extends WebException {

    public _400SerializationException(final Class<?> clazz,
                                      final Object value) {
        super(clazz, value.getClass());
    }

    @Override
    public int getCode() {
        return -20019;
    }
}