package io.spring.up.boot.converter;

import io.spring.up.aiki.Ux;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JObjectResponser implements Responser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JObjectResponser.class);

    @Override
    public JsonObject process(final Object value) {
        final JsonObject data = ((JsonObject) value).copy();
        Log.updg(LOGGER, "JsonObject for value = {0}", data.encode());
        return new JsonObject().put("data", Ux.outKey(data));
    }
}
