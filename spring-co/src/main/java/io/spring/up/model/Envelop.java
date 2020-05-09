package io.spring.up.model;

import io.spring.up.cv.Constants;
import io.spring.up.exception.WebException;
import io.spring.up.exception.web._500InternalServerException;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.springframework.http.HttpStatus;

public class Envelop {

    private final WebException error;
    private final JsonObject data;
    private final HttpStatus status;


    private <T> Envelop(final T data, final HttpStatus status) {
        final JsonObject serialized = Ut.serializeJson(data);
        final JsonObject bodyData = new JsonObject();
        bodyData.put(Constants.DATA, serialized);
        this.data = bodyData;
        this.error = null;
        this.status = status;
    }

    private Envelop(final WebException error) {
        this.status = error.getStatus();
        this.error = error;
        this.data = error.toJson();
    }

    public static <T> Envelop success(final T entity) {
        return new Envelop(entity, HttpStatus.OK);
    }

    public static <T> Envelop success(final T entity, final HttpStatus status) {
        return new Envelop(entity, status);
    }

    public static Envelop failure(final String message) {
        return new Envelop(new _500InternalServerException(Envelop.class, message));
    }

    public static Envelop failure(final Throwable ex) {
        return new Envelop(new _500InternalServerException(Envelop.class, ex.getMessage()));
    }

    public static Envelop failure(final WebException error) {
        return new Envelop(error);
    }

    @SuppressWarnings("unchecked")
    public <T> T data() {
        return Fn.getNull(null, () -> {
            if (this.data.containsKey(Constants.DATA)) {
                final Object value = this.data.getValue(Constants.DATA);
                if (null != value) {
                    return (T) value;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }, this.data);
    }

    public JsonObject json() {
        final JsonObject data = this.data();
        if (null == data) {
            return null == this.error ? null : this.error.toJson();
        } else {
            return data;
        }
    }
}
