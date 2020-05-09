package io.spring.up.exception;

import io.spring.up.cv.Strings;
import io.spring.up.log.Errors;

public abstract class PluginException extends AbstractException{

    private final String message;

    public PluginException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
        this.message = Errors.formatPlugin(clazz, this.getCode(), args);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
