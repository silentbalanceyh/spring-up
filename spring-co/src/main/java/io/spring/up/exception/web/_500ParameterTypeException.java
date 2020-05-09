package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500ParameterTypeException extends WebException {

    public _500ParameterTypeException(final Class<?> clazz,
                                      final Class<?> type) {
        super(clazz, type);
    }

    @Override
    public int getCode() {
        return -20002;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
