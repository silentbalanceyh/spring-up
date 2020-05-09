package io.spring.up.ipc.core;

import io.reactivex.Observable;
import io.spring.up.aiki.Ux;
import io.spring.up.exception.web._424InvokerMissingException;
import io.spring.up.exception.web._424IpcSelectorInitException;
import io.spring.up.ipc.core.invoker.Invoker;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IpcSelector {

    private static final ConcurrentMap<String, IpcSelector> SELECTORS =
            new ConcurrentHashMap<>();
    private transient final Object reference;
    private transient final Method method;
    private transient Invoker invoker;

    public static IpcSelector init(final String address) {
        return Fn.pool(SELECTORS, address, () -> new IpcSelector(address));
    }

    @SuppressWarnings("unchecked")
    private IpcSelector(final String address) {
        this.reference = IpcScanner.getProxies(address);
        Ux.out(null == this.reference, _424IpcSelectorInitException.class, this.getClass(), address);
        final Method method = IpcScanner.getScanned(address);
        Ux.out(null == method, _424IpcSelectorInitException.class, this.getClass(), address);
        this.invoker = this.initInvoker(method);
        Ux.out(null == this.invoker, _424InvokerMissingException.class, this.getClass(), method);
        this.method = null == this.invoker ? null : method;
    }

    private Invoker initInvoker(final Method method) {
        assert 1 == method.getParameterTypes().length : "[ UPA ] Parameter Type Length must be 1";
        final Class<?> parameterType = method.getParameterTypes()[0];
        return Invoker.INVOKERS.getOrDefault(parameterType, null);
    }

    public static boolean filterMethod(final Method method) {
        return !Observable.fromIterable(Invoker.INVOKERS.values())
                .filter(item -> item.ensure(method))
                .reduce(new HashSet<Invoker>(), (set, checked) -> {
                    set.add(checked);
                    return set;
                }).blockingGet().isEmpty();
    }

    public JsonObject invoke(final Object... params) {
        this.invoker = this.invoker.bind(this.method).bind(this.reference);
        return this.invoker.invoke(params);
    }
}
