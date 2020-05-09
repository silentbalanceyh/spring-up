package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500JsonResponseException extends WebException {

    public _500JsonResponseException(final Class<?> clazz,
                                     final Throwable ex) {
        super(clazz, ex.getMessage());
    }

    @Override
    public int getCode() {
        return -20005;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
