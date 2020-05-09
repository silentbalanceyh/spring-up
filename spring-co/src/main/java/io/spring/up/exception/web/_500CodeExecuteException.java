package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _500CodeExecuteException extends WebException {

    public _500CodeExecuteException(final Class<?> clazz,
                                    final Throwable ex) {
        super(clazz, ex.getMessage());
        // TODO: 后期修改
        ex.printStackTrace();
    }

    @Override
    public int getCode() {
        return -20018;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
