package io.spring.up.core.rules;

import io.spring.up.exception.WebException;
import io.spring.up.exception.web._400ValidationException;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;

import java.util.function.Supplier;

public interface Rule {
    /**
     * 字段验证，值，配置
     *
     * @param field  字段名
     * @param value  字段值
     * @param config 配置信息
     * @return 产生的Exception
     */
    WebException verify(final String field,
                        final Object value,
                        final JsonObject config);

    /**
     * 读取Rule相关信息
     *
     * @param type 类型
     * @return 返回Rule
     */
    static Rule get(final String type) {
        return Fn.getNull(() -> Pool.RULE_REF_MAP.get(type), type);
    }

    static WebException verify(final Class<?> clazz,
                               final Supplier<Boolean> errorSupplier,
                               final String field, final Object value,
                               final String rule, final JsonObject config) {
        WebException error = null;
        if (errorSupplier.get()) {
            error = new _400ValidationException(clazz, field, value, rule, config);
        }
        return error;
    }
}
