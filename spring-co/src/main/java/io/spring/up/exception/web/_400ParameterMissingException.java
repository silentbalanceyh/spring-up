package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400ParameterMissingException extends WebException {

    public _400ParameterMissingException(final Class<?> clazz,
                                         final String name,
                                         final String method) {
        super(clazz, name, method);
    }

    @Override
    public int getCode() {
        return -20003;
    }
}
