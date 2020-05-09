package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500InternalServerException extends WebException {

    public _500InternalServerException(final Class<?> clazz,
                                       final String details) {
        super(clazz, details);
    }

    @Override
    public int getCode() {
        return -20015;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
