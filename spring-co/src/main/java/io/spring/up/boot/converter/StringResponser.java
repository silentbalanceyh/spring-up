package io.spring.up.boot.converter;

import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringResponser implements Responser {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringResponser.class);

    @Override
    public JsonObject process(final Object value) {
        Log.updg(LOGGER, "String for value = {0}", value);
        return new JsonObject().put("data", value);
    }
}
