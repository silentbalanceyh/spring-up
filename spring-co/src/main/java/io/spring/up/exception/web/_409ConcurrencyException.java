package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

public class _409ConcurrencyException extends WebException {

    public _409ConcurrencyException(final Class<?> clazz,
                                    final DataAccessException source) {
        super(clazz, source.getMessage(), source.getClass().getName());
    }

    @Override
    public int getCode() {
        return -20006;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
