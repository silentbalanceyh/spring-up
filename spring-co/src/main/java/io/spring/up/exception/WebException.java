package io.spring.up.exception;

import io.spring.up.cv.Strings;
import io.spring.up.log.Errors;
import io.vertx.core.json.JsonObject;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public abstract class WebException extends AbstractException {

    protected static final String INFO = "info";

    private final String message;

    protected HttpStatus status;

    private String info;

    public WebException(final String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public WebException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
        this.message = Errors.formatWeb(clazz, this.getCode(), args);
        this.info = Errors.formatReadible(clazz, this.getCode());
        this.status = HttpStatus.BAD_REQUEST;
    }

    @Override
    public abstract int getCode();

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        // Default exception for 400
        return this.status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    @Override
    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(CODE, this.getCode());
        data.put(MESSAGE, this.getMessage());
        if (Objects.nonNull(this.info)) {
            data.put(INFO, this.info);
        }
        return data;
    }
}
