package io.spring.up.quiz;

import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    protected String getFile(final String filename) {
        final Class<?> clazz = this.getClass();
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        Log.upt(this.getLogger(), "File: {0}", file);
        return file;
    }

    protected JsonObject getJObject(final String filename) {
        return Ut.ioJObject(this.getFile(filename));
    }

    protected JsonArray getJArray(final String filename) {
        return Ut.ioJArray(this.getFile(filename));
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }
}
