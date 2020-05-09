package io.spring.up.ipc.core;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.spring.up.annotations.Ipc;
import io.spring.up.log.Log;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

public class IpcScanner extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpcScanner.class);
    private static final ConcurrentMap<String, Method> SCANNED
            = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Object> PROXIES
            = new ConcurrentHashMap<>();
    private transient final HashSet<String> scannedSet
            = new HashSet<>();

    private transient final Class<?> target;
    private transient final Object reference;
    private transient final CountDownLatch counter;

    public IpcScanner(final Object reference, final CountDownLatch counter) {
        this.reference = reference;
        this.target = reference.getClass();
        this.setName("ipc-scanner-" + this.getId());
        this.counter = counter;
    }

    public static ConcurrentMap<String, Method> getScanned() {
        return SCANNED;
    }

    public static Method getScanned(final String address) {
        return SCANNED.getOrDefault(address, null);
    }

    public static ConcurrentMap<String, Object> getProxies() {
        return PROXIES;
    }

    public static Object getProxies(final String address) {
        return PROXIES.getOrDefault(address, null);
    }

    @Override
    public void run() {
        Log.info(LOGGER, "[ IPC ] Ipc Scanner started: {0} for {1}", this.getName(), this.target);
        Fn.safeNull(() -> Observable.fromArray(this.target.getDeclaredMethods())
                        .filter(item -> item.isAnnotationPresent(Ipc.class))
                        .map(method -> Maybe.just(method)
                                .map(item -> item.getAnnotation(Ipc.class))
                                .map(item -> Ut.invoke(item, "value"))
                                .filter(Objects::nonNull)
                                .map(item -> (String) item)
                                .filter(item -> !Ut.isNil(item))
                                .map(this::put)
                                .map(item -> this.put(item, method))
                                .blockingGet()
                        )
                        .subscribe(this::countDown).dispose(),
                this.target);
    }

    private String put(final String item) {
        PROXIES.put(item, this.reference);
        return item;
    }

    private String put(final String item, final Method method) {
        SCANNED.put(item, method);
        this.scannedSet.add(item);
        return item;
    }

    private void countDown(final String item) {
        final long size = Arrays.stream(this.target.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Ipc.class)).count();
        if (SCANNED.containsKey(item) && size == this.scannedSet.size()) {
            // 满足条件
            // 1.已经将item添加到SCANNED中了
            // 2.SCANNED扫描出来的值和当前期望值一致
            this.counter.countDown();
        }
    }
}
