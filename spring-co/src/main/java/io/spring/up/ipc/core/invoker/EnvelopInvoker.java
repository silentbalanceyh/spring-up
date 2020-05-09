package io.spring.up.ipc.core.invoker;

import io.spring.up.model.Envelop;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

import java.lang.reflect.Method;

public class EnvelopInvoker implements Invoker {
    private transient Method method;
    private transient Object reference;

    @Override
    public JsonObject invoke(final Object... params) {
        final Object invoked = Ut.invoke(this.reference, this.method.getName(), params);
        return Ensurer.uniformReturn(invoked, JsonObject::new);
    }

    @Override
    public Invoker bind(final Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Invoker bind(final Object reference) {
        this.reference = reference;
        return this;
    }

    @Override
    public boolean ensure(final Method method) {
        return Ensurer.checkReturnType(method, JsonObject.class, Envelop.class)
                && Ensurer.checkParameterLength(method, 1)
                && Ensurer.checkParameterTypes(method, Envelop.class);
    }
}
