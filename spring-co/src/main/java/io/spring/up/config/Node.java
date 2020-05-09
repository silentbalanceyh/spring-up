package io.spring.up.config;

import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;

public interface Node<T> {

    T read();

    static JsonObject infix(final String filename) {
        final Node<JsonObject> node =
                Fn.pool(Pool.REFERENCES, filename, () -> new ConfigNode(filename));
        return node.read();
    }
}
