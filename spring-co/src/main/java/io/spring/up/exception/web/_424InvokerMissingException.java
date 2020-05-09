package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Method;

public class _424InvokerMissingException extends WebException {

    public _424InvokerMissingException(final Class<?> clazz,
                                       final Method method) {
        super(clazz, method);
    }

    @Override
    public int getCode() {
        return -20017;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FAILED_DEPENDENCY;
    }
}
