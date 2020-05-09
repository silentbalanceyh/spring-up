package io.spring.up.exception;

import io.spring.up.cv.Strings;
import io.spring.up.log.Errors;

public abstract class UpException extends AbstractException {

    private final String message;

    public UpException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
        this.message = Errors.formatUp(clazz, this.getCode(), args);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
