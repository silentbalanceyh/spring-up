package io.spring.up.config;

import io.vertx.core.json.JsonObject;

/**
 *
 */
public interface InfixData<T> {

    JsonObject toJson();

    T fromJson(JsonObject config);
}
