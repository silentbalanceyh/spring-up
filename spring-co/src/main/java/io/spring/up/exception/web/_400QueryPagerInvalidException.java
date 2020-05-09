package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400QueryPagerInvalidException extends WebException {

    public _400QueryPagerInvalidException(final Class<?> clazz,
                                          final String key) {
        super(clazz, key);
    }

    @Override
    public int getCode() {
        return -60023;
    }
}
