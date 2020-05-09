package io.spring.up.boot.converter;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.spring.up.aiki.Ux;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataResponser implements Responser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataResponser.class);

    @Override
    public JsonObject process(final Object value) {
        final JsonObject original = new JsonObject();
        Log.updg(LOGGER, "Value before serialization type = {1}, literal = {0}, original = {2}",
                value,
                null != value ? value.getClass() : null,
                original);
        final Iterable json = Ut.serializeJson(value);
        Log.updg(LOGGER, "Value after serialization: {0}", json);
        return Fn.getNull(new JsonObject().put("data", "null"), () -> {
            if (json instanceof JsonObject) {
                final JsonObject response = (JsonObject) json;
                return original.copy().put("data", Ux.outKey(response));
            } else {
                final JsonArray response = Ux.outKey((JsonArray) json);
                return original.copy().put("list", response).put("count", response.size());
            }
        }, original);
    }


    private Object syncData(final Object value) {
        Object dataValue = null;
        if (value != null) {
            final Class<?> dataClass = value.getClass();
            Log.up(LOGGER, "Detected async flow class = {0}!", dataClass);
            if (Single.class == dataClass) {
                Log.up(LOGGER, "Single = {0}!", value);
                dataValue = ((Single<?>) value).blockingGet();
            } else if (Observable.class == dataClass) {
                Log.up(LOGGER, "Observable = {0}!", value);
                dataValue = ((Observable<?>) value).blockingSingle();
            } else if (Flowable.class == dataClass) {
                Log.up(LOGGER, "Flowable = {0}!", value);
                final Object flowable = ((Flowable<?>) value).blockingSingle();
                System.out.println(flowable);
                System.out.println(value);
            } else {
                dataValue = value;
            }
        }
        return dataValue;
    }
}
