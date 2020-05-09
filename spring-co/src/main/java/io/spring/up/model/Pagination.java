package io.spring.up.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

public class Pagination implements Serializable {

    @JsonProperty("count")
    private final Integer count;

    @JsonProperty("list")
    private final JsonArray list;

    public Integer getCount() {
        return this.count;
    }

    public JsonArray getList() {
        return this.list;
    }

    private Pagination(final JsonObject data) {
        this.list = null == data.getValue("list") ? new JsonArray() : data.getJsonArray("list");
        this.count = null == data.getValue("count") ? 0 : data.getInteger("count");
    }

    private Pagination(final Integer count, final JsonArray list) {
        this.list = null == list ? new JsonArray() : list;
        this.count = null == count ? 0 : count;
    }

    public static Pagination create() {
        return create(new JsonObject());
    }

    public static Pagination create(final JsonObject data) {
        return new Pagination(data);
    }

    public static Pagination create(final Integer count, final JsonArray list) {
        return new Pagination(count, list);
    }

    public JsonObject toJson() {
        return new JsonObject().put("list", this.list).put("count", this.count);
    }
}
