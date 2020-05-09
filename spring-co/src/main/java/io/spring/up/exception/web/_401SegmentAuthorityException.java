package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _401SegmentAuthorityException extends WebException {

    public _401SegmentAuthorityException(final Class<?> clazz,
                                         final String raw) {
        super(clazz, raw);
    }

    @Override
    public int getCode() {
        return -20014;
    }
}
