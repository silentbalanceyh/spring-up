package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

public class _401UnsupportedAuthorityException extends WebException {

    public _401UnsupportedAuthorityException(final Class<?> clazz,
                                             final String user,
                                             final Integer counter) {
        super(clazz, user, counter);
    }

    @Override
    public int getCode() {
        return -20013;
    }
}
