package io.spring.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;

class Json {

    static JsonObject convert(final JsonObject input, final String from, final String to) {
        return Fn.getJvm(new JsonObject(), () -> {
            final JsonObject processed = input.copy();
            for (final String field : input.fieldNames()) {
                final Object value = input.getValue(field);
                if (Ut.isJObject(value)) {
                    processed.put(field, convert((JsonObject) value, from, to));
                } else if (Ut.isJArray(value)) {
                    processed.put(field, convert((JsonArray) value, from, to));
                } else {
                    if (field.equals(from)) {
                        processed.put(to, processed.getValue(field));
                        // modified by Hongwe: Deserialize not working for @JsonProperty
                        // processed.remove(from);
                    }
                }
            }
            return processed;
        }, input, from, to);
    }

    static JsonArray convert(final JsonArray input, final String from, final String to) {
        return Fn.getJvm(new JsonArray(), () -> {
            final JsonArray converted = new JsonArray();
            input.stream().forEach(item -> {
                if (null != item) {
                    if (Ut.isJObject(item)) {
                        converted.add(convert((JsonObject) item, from, to));
                    } else {
                        converted.add(item);
                    }
                }
            });
            return converted;
        }, input, from, to);
    }
}
