package io.spring.up.boot.converter;

import io.spring.up.model.Pagination;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class PaginationResponser implements Responser {

    @Override
    public JsonObject process(final Object value) {
        final Pagination pagination = (Pagination) value;
        final JsonObject data = pagination.toJson().copy();
        final Responser listProcessor = Responser.get(JsonArray.class);
        data.put("list", listProcessor.process(
                data.getJsonArray("list")).getJsonArray("list"));
        return data;
    }
}
