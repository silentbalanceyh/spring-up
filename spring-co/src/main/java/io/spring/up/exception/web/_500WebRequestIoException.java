package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500WebRequestIoException extends WebException {

    public _500WebRequestIoException(final Class<?> clazz, final Throwable ex) {
        super(clazz, null == ex ? null : ex.getMessage());
    }

    @Override
    public int getCode() {
        return -20001;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
