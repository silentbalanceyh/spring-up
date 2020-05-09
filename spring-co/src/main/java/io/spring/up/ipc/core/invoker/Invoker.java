package io.spring.up.ipc.core.invoker;

import io.spring.up.model.Envelop;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface Invoker {

    JsonObject invoke(Object... params);

    Invoker bind(Method method);

    Invoker bind(Object reference);

    boolean ensure(Method method);

    ConcurrentMap<Class<?>, Invoker> INVOKERS = new ConcurrentHashMap<Class<?>, Invoker>() {
        {
            this.put(JsonObject.class, Ut.singleton(JsonInvoker.class));
            this.put(Envelop.class, Ut.singleton(EnvelopInvoker.class));
        }
    };
}
