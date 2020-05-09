package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class _404NoElementException extends WebException {

    public _404NoElementException(final Class<?> clazz,
                                  final NoSuchElementException source) {
        super(clazz, source.getMessage(), source.getClass().getName());
    }

    @Override
    public int getCode() {
        return -20008;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
