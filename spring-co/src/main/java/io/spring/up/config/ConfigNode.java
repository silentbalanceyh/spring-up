package io.spring.up.config;

import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigNode implements Node<JsonObject> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigNode.class);

    private transient final String key;

    ConfigNode(final String key) {
        this.key = key;
    }

    @Override
    public JsonObject read() {
        // 读取原始文件
        final JsonObject merged = new JsonObject();
        // 框架内文件和原始文件合并
        final JsonObject direct = this.readDirect(this.key);
        final JsonObject original = this.readInternal(this.key);
        if (null != direct && !direct.isEmpty()) {
            merged.mergeIn(direct, true);
        }
        if (null != original && !original.isEmpty()) {
            merged.mergeIn(original, true);
        }
        Log.debug(LOGGER, "[ UP DG ] Config Loaded Successfully: {0} = {1}", this.key, merged.fieldNames());
        return merged;
    }

    private JsonObject readInternal(final String key) {
        final String filename = "internal/application-" + key + ".yml";
        return Fn.pool(Pool.INTERNAL, filename,
                () -> Ut.ioYaml(filename));
    }

    private JsonObject readDirect(final String key) {
        final String filename = "application-" + key + ".yml";
        return Fn.pool(Pool.CONFIG, filename,
                () -> Ut.ioYaml(filename));
    }
}
