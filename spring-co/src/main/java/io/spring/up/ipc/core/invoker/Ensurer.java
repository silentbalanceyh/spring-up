package io.spring.up.ipc.core.invoker;

import io.spring.up.model.Envelop;
import io.vertx.core.json.JsonObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.function.Supplier;

class Ensurer {

    static JsonObject uniformReturn(final Object result, final Supplier<JsonObject> supplier) {
        if (null == result) {
            return new JsonObject();
        }
        if (result instanceof JsonObject) {
            return (JsonObject) result;
        } else if (result instanceof Envelop) {
            return ((Envelop) result).json();
        } else {
            return supplier.get();
        }
    }

    static boolean checkReturnType(final Method method, final Class<?>... expected) {
        final Class<?> returnType = method.getReturnType();
        boolean result = false;
        for (final Class<?> expectedItem : expected) {
            if (expectedItem == returnType) {
                result = true;
                break;
            }
        }
        return result;
    }

    static boolean checkParameterLength(final Method method, final int expected) {
        final Parameter[] parameters = method.getParameters();
        if (expected != parameters.length) {
            return false;
        }
        return true;
    }

    static boolean checkParameterTypes(final Method method, final Class<?>... expected) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        boolean result = true;
        for (int idx = 0; idx < expected.length; idx++) {
            final Class<?> expectedItem = expected[idx];
            final Class<?> paramItem = parameterTypes[idx];
            if (expectedItem != paramItem) {
                result = false;
                break;
            }
        }
        return result;
    }
}
