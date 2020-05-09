package io.spring.up.boot.converter;

import io.spring.up.aiki.Ux;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JArrayResponser implements Responser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JArrayResponser.class);

    @Override
    public JsonObject process(final Object value) {
        final JsonArray data = ((JsonArray) value).copy();
        Log.updg(LOGGER, "JsonArray for {0}", data.encode());
        final JsonArray result = Ux.outKey(data);
        return new JsonObject().put("list", result).put("count", result.size());
    }
}
