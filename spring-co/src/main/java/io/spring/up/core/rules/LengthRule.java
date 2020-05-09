package io.spring.up.core.rules;

import io.spring.up.exception.WebException;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;

public class LengthRule implements Rule {
    @Override
    public WebException verify(final String field,
                               final Object value,
                               final JsonObject config) {
        return Fn.getNull(() -> Rule.verify(this.getClass(), () -> {
            // 包含最小值
            // final Integer min = Ut.readInt(null, config, "min");
            // final Integer max = Ut.readInt(null, config, "max");
            // final Integer numberVal = value.toString().length();
            // 不在范围内，这里提供的是Error Condition
            return false; // !Ut.inRange(numberVal, min, max);
        }, field, value, "length", config), config);
    }
}
