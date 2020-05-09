package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500QueryMetaNullException extends WebException {

    public _500QueryMetaNullException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -60024;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
