package io.spring.up.aiki;

import io.spring.up.annotations.Ipc;
import io.spring.up.ipc.core.IpcSelector;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Scanner {
    // 后边放到配置文件中
    private static final String[] IGNORES = new String[]{
            "org.springframework",
            "com.netflix",
            "org.apache",
            "springfox",
            "com.hazelcast",
            "com.sun",
            "com.fasterxml",
            "org.zalando",
            "com.ryantenney",
            "net",
            "io",
            "java",
            "javax",
            "feign",
            "org.thymeleaf",
            "com.google",
            "com.zaxxer"
    };
    
    static synchronized boolean isValid(final Class<?> clazz) {
        final Set<String> sets = new HashSet<>();
        for (final String prefix : IGNORES) {
            if (clazz.getName().startsWith(prefix)
                    || 0 < clazz.getName().indexOf('$')) {
                sets.add(clazz.getName());
                break;
            }
            if (!clazz.isAnnotationPresent(Component.class)) {
                sets.add(clazz.getName());
                break;
            }
            final Method[] methods = clazz.getDeclaredMethods();
            final Set<Method> methodSet = Arrays.stream(methods)
                    .filter(item -> item.isAnnotationPresent(Ipc.class))
                    .filter(IpcSelector::filterMethod)
                    .collect(Collectors.toSet());
            if (methodSet.isEmpty()) {
                sets.add(clazz.getName());
                break;
            }
        }
        return sets.isEmpty();
    }
}
