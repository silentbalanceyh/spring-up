package io.spring.up.core.rules;

import io.spring.up.exception.web._400ValidationException;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class RulerTc {

    @Test(expected = _400ValidationException.class)
    public void testRequired() {
        final JsonObject data = new JsonObject().put("username", "admin");
        Ruler.verify("enterprise", data);
    }

    @Test(expected = _400ValidationException.class)
    public void testArray() {
        final JsonObject data = new JsonObject().put("menuIds", "Hello");
        Ruler.verify("registery", data);
    }
}
