package io.spring.up.boot.converter;

import io.reactivex.Single;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxSingleResponser implements Responser {
    private static final Logger LOGGER = LoggerFactory.getLogger(RxSingleResponser.class);

    @Override
    public JsonObject process(final Object value) {
        final Single<?> single = (Single<?>) value;
        final Object target = single.blockingGet();
        Log.updg(LOGGER, "RxSingle for = {0}", target);
        return Responser.get(Object.class).process(target);
    }
}
