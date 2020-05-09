package io.spring.up.exception.web;

import io.spring.up.exception.WebException;

import java.lang.reflect.Method;

public class _500MethodSignatureException extends WebException {

    public _500MethodSignatureException(final Class<?> clazz,
                                        final Method method) {
        super(clazz, method.getName(), method.getDeclaringClass().getName());
    }

    @Override
    public int getCode() {
        return -60025;
    }
}
