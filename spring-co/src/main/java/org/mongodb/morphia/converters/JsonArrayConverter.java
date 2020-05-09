package org.mongodb.morphia.converters;

import io.vertx.core.json.JsonArray;
import org.mongodb.morphia.mapping.MappedField;

public class JsonArrayConverter extends TypeConverter {

    public JsonArrayConverter() {
        super(JsonArray.class);
    }

    @Override
    public Object encode(final Object input, final MappedField optionalExtraInfo) {
        if (null == input) {
            return null;
        }
        final JsonArray value = (JsonArray) input;
        return value.encode();
    }

    @Override
    public Object decode(final Class<?> aClass,
                         final Object o,
                         final MappedField mappedField) {
        if (null == o) {
            return new JsonArray();
        }
        return new JsonArray(o.toString());
    }
}
