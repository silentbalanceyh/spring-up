package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500FunctionExecuteException extends WebException {

    public _500FunctionExecuteException(final Class<?> clazz,
                                        final Throwable source) {
        super(clazz, source.getMessage(), source.getClass().getName());
    }

    @Override
    public int getCode() {
        return -20010;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
