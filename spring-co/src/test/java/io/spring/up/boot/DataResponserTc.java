package io.spring.up.boot;

import io.spring.up.boot.converter.DataResponser;
import io.spring.up.boot.converter.Responser;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataResponserTc {

    private final transient Responser responser = new DataResponser();

    private void execute(final Object value) {
        final JsonObject data = new JsonObject();
        final JsonObject result = this.responser.process(value);
        System.out.println(result.encodePrettily());
    }

    @Test
    public void testString() {
        this.execute("Hello World");
    }

    @Test
    public void testNumber() {
        this.execute(1);
    }

    @Test
    public void testList() {
        final List<User> list = new ArrayList<>();
        list.add(new User("A", "A"));
        list.add(new User("B", "B"));
        this.execute(list);
    }

    @Test
    public void testJsonArray() {
        final JsonArray array = new JsonArray();
        array.add(new JsonObject().put("A", "a"));
        array.add(new JsonObject().put("H", "h"));
        this.execute(array);
    }
}

class User {
    private String name;
    private String email;
    private String id;

    public User(final String name, final String email) {
        this.email = email;
        this.name = name;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
