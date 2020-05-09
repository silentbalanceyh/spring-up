package io.spring.up.boot.converter;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

public class CommonResponser implements Responser {

    @Override
    public JsonObject process(final Object object) {
        final String literal = Ut.serialize(object);
        if (Ut.isJObject(literal) || Ut.isJArray(literal)) {
            final Iterable json = Ut.serializeJson(object);
            if (Ut.isJObject(literal)) {
                return Responser.get(JsonObject.class).process(json);
            } else {
                return Responser.get(JsonArray.class).process(json);
            }
        } else {
            return Responser.get(String.class).process(object);
        }
    }
}
