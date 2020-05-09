package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _400QueryParamTypeException extends WebException {

    public _400QueryParamTypeException(final Class<?> clazz,
                                       final String key,
                                       final Class<?> expectedCls,
                                       final Class<?> currentCls) {
        super(clazz, key, expectedCls, currentCls);
    }

    @Override
    public int getCode() {
        return -60022;
    }
}
