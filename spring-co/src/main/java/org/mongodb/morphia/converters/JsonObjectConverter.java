package org.mongodb.morphia.converters;

import io.vertx.core.json.JsonObject;
import org.mongodb.morphia.mapping.MappedField;

public class JsonObjectConverter extends TypeConverter {

    public JsonObjectConverter() {
        super(JsonObject.class);
    }

    @Override
    public Object encode(final Object input, final MappedField optionalExtraInfo) {
        if (null == input) {
            return null;
        }
        final JsonObject value = (JsonObject) input;
        return value.encode();
    }

    @Override
    public Object decode(final Class<?> aClass,
                         final Object o,
                         final MappedField mappedField) {
        if (null == o) {
            return new JsonObject();
        }
        return new JsonObject(o.toString());
    }
}
