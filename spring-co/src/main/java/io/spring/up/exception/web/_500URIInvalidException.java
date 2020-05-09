package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500URIInvalidException extends WebException {

    public _500URIInvalidException(final Class<?> clazz, final String uri) {
        super(clazz, uri);
    }

    @Override
    public int getCode() {
        return -20009;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

