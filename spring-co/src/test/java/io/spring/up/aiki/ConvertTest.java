package io.spring.up.aiki;

import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void testConvert() {
        final JsonObject data = Ut.ioJObject("test/data.json");
        final JsonObject converted = Ux.outKey(data);
        System.out.println(converted.encodePrettily());
    }

    @Test
    public void testConvert1() {
        final JsonObject data = Ut.ioJObject("test/in.json");
        final JsonObject converted = Ux.inKey(data);
        System.out.println(converted.encodePrettily());
    }

    @Test
    public void testBrokenPipe() {
        final JsonObject data = Ut.ioJObject("test/broken.pipe.json");
        for (int idx = 0; idx < 10; idx++) {
            final String string = Single.just(Ut.serialize(data)).blockingGet();
            System.out.println(string);
        }
    }
}
