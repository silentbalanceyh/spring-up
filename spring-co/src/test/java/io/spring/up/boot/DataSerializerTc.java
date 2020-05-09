package io.spring.up.boot;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.spring.up.boot.converter.Responser;
import io.spring.up.log.Log;
import io.spring.up.model.Pagination;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.UUID;

public class DataSerializerTc {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSerializerTc.class);

    @Test
    public void testJsonObject() {
        final Object data = new JsonObject().put("id", "UUID");
        final Responser responser = Responser.get(data.getClass());
        final JsonObject result = responser.process(data);
        // Expected
        final JsonObject expected = new JsonObject().put("data", new JsonObject().put("key", "UUID"));
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for JsonObject class.");
    }

    @Test
    public void testJsonArray() {
        final Object data = new JsonArray().add(new JsonObject().put("id", "UUID"));
        final Responser responser = Responser.get(data.getClass());
        final JsonObject result = responser.process(data);
        // Expected
        final JsonObject expected = new JsonObject()
                .put("list", new JsonArray().add(new JsonObject().put("key", "UUID")))
                .put("count", 1);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for JsonArray class.");
    }

    @Test
    public void testString() {
        final String data = "Hello World";
        final Responser responser = Responser.get(data.getClass());
        final JsonObject result = responser.process(data);
        final JsonObject expected = new JsonObject()
                .put("data", "Hello World");
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for String class.");
    }

    @Test
    public void testInteger() {
        final Integer data = 1;
        final Responser responser = Responser.get(data.getClass());
        final JsonObject result = responser.process(data);
        final JsonObject expected = new JsonObject()
                .put("data", 1);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Integer class.");
    }

    @Test
    public void testSingle() {
        final String data = "Hello";
        final Single<String> single = Single.just(data);
        final Responser responser = Responser.get(single.getClass());
        final JsonObject result = responser.process(single);
        final JsonObject expected = new JsonObject()
                .put("data", data);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Single<String> (just) class.");
    }

    @Test
    public void testSingle1() {
        final String data = "Hello";
        final Single<String> single = Single.fromFuture(AsyncResult.forValue(data));
        final Responser responser = Responser.get(single.getClass());
        final JsonObject result = responser.process(single);
        final JsonObject expected = new JsonObject()
                .put("data", data);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Single<String> (fromFuture) class.");
    }

    @Test
    public void testPojo() {
        final User user = new User("Lang", "lang.yu@hpe.com");
        final Responser responser = Responser.get(user.getClass());
        final JsonObject result = responser.process(user);
        final JsonObject userJson = Ut.serializeJson(user);
        final JsonObject expected = new JsonObject()
                .put("data", userJson);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Pojo {0} class.", user.getClass());
    }

    @Test
    public void testPojoWithKey() {
        final User user = new User("Lang", "lang.yu@hpe.com");
        user.setId(UUID.randomUUID().toString());
        final Responser responser = Responser.get(user.getClass());
        final JsonObject result = responser.process(user);
        final JsonObject userJson = Ut.serializeJson(user);
        userJson.put("key", userJson.getString("id"));
        userJson.remove("id");
        final JsonObject expected = new JsonObject()
                .put("data", userJson);
        Assert.assertEquals(expected, result);
        Log.info(LOGGER, "[ UP ] Successfully for Pojo {0} class.", user.getClass());
    }


    @Test
    public void testFlow() {
        final String data = "Hello";
        final Flowable<String> flowable = Flowable.fromFuture(AsyncResult.forValue(data));
        final Responser responser = Responser.get(flowable.getClass());
        final JsonObject result = responser.process(flowable);
        final JsonObject expected = new JsonObject()
                .put("data", data);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Flowable<String> (fromFuture) class.");
    }

    @Test
    public void testPagination() {
        final Pagination pagination = Pagination.create(1, new JsonArray().add("Hello"));
        final Responser responser = Responser.get(pagination.getClass());
        final JsonObject result = responser.process(pagination);
        final String content = "{\"list\":[\"Hello\"],\"count\":1}";
        final JsonObject expected = new JsonObject(content);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Pagination ( Literal ) class.");
    }

    @Test
    public void testComplexPagination() {
        final Pagination pagination = Pagination.create(1, new JsonArray().add(
                new JsonObject().put("id", "id-uuid")
                        .put("name", "Lang")
        ));
        final Responser responser = Responser.get(pagination.getClass());
        final JsonObject result = responser.process(pagination);
        final String content = "{\"list\":[{\"name\":\"Lang\",\"key\":\"id-uuid\"}],\"count\":1}";
        final JsonObject expected = new JsonObject(content);
        Assert.assertEquals(expected, result);
        LOGGER.info("[ UP ] Successfully for Pagination class.");
    }

    @Test
    public void serializeJson() {
        final Pagination pagination = Pagination.create(1, new JsonArray().add(
                new JsonObject().put("id", "id-uuid")
                        .put("name", "Lang")
        ));
        final JsonObject object = Ut.serializeJson(pagination);
        LOGGER.info("[ UP ] Successfully for Pagination object with jackson directly");
    }
}
