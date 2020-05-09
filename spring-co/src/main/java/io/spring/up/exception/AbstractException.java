package io.spring.up.exception;

import io.vertx.core.json.JsonObject;

public abstract class AbstractException extends RuntimeException {

    protected static final String MESSAGE = "message";
    protected static final String CODE = "code";

    public AbstractException(final String message) {
        super(message);
    }

    public AbstractException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AbstractException(final Throwable cause) {
        super(cause);
    }

    protected abstract int getCode();

    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(CODE, this.getCode());
        data.put(MESSAGE, this.getMessage());
        return data;
    }
}
