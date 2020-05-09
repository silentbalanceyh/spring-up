package io.spring.up.core.rules;

import io.spring.up.exception.WebException;
import io.vertx.core.json.JsonObject;

public class RequiredRule implements Rule {
    @Override
    public WebException verify(final String field,
                               final Object value,
                               final JsonObject config) {
        return Rule.verify(this.getClass(), () -> null == value, field, value, "required", config);
    }
}
