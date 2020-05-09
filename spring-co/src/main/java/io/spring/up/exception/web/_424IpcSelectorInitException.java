package io.spring.up.exception.web;

import io.spring.up.exception.WebException;
import org.springframework.http.HttpStatus;

public class _424IpcSelectorInitException extends WebException {

    public _424IpcSelectorInitException(final Class<?> clazz,
                                        final String address) {
        super(clazz, address);
    }

    @Override
    public int getCode() {
        return -20016;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FAILED_DEPENDENCY;
    }
}
