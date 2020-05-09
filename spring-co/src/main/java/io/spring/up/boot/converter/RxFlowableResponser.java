package io.spring.up.boot.converter;

import io.reactivex.Flowable;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxFlowableResponser implements Responser {
    private static final Logger LOGGER = LoggerFactory.getLogger(RxFlowableResponser.class);

    @Override
    public JsonObject process(final Object value) {
        final Flowable<?> single = (Flowable<?>) value;
        final Object target = single.blockingSingle();
        Log.updg(LOGGER, "RxFlowable for = {0}", target);
        return Responser.get(Object.class).process(target);
    }
}
