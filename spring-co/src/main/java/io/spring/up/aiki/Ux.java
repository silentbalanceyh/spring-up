package io.spring.up.aiki;

import io.grpc.Channel;
import io.spring.up.cv.Constants;
import io.spring.up.exception.WebException;
import io.spring.up.ipc.model.IpcRequest;
import io.spring.up.ipc.model.IpcResponse;
import io.spring.up.model.Envelop;
import io.spring.up.query.Query;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.converters.Converters;
import org.mongodb.morphia.converters.JsonArrayConverter;
import org.mongodb.morphia.converters.JsonObjectConverter;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

public class Ux {

    public static <T> HashSet<T> reduceSet(final HashSet<T> collection, final T element) {
        return Reduce.rdcHashSet(collection, element);
    }

    // ID处理方法
    public static JsonObject inKey(final JsonObject input) {
        return Json.convert(input, "key", "id");
    }

    public static JsonArray inKey(final JsonArray array) {
        return Json.convert(array, "key", "id");
    }

    public static JsonObject outKey(final JsonObject input) {
        return Json.convert(input, "id", "key");
    }

    public static JsonArray outKey(final JsonArray array) {
        return Json.convert(array, "id", "key");
    }

    public static void out(final Supplier<Boolean> condFun,
                           final Class<?> clazz, final Object... args) {
        final Boolean checked = condFun.get();
        out(checked, clazz, args);
    }

    public static void out(final Boolean condFun,
                           final Class<?> clazz, final Object... args) {
        if (condFun) {
            throw (WebException) Ut.instance(clazz, args);
        }
    }

    // Rpc专用方法
    public static class Rpc {
        public static IpcRequest request(final JsonObject data) {
            return io.spring.up.aiki.Rpc.in(data);
        }

        public static IpcRequest request(final Envelop envelop) {
            return io.spring.up.aiki.Rpc.in(envelop);
        }

        public static IpcResponse response(final JsonObject data) {
            return io.spring.up.aiki.Rpc.out(data);
        }

        public static IpcResponse response(final Envelop envelop) {
            return io.spring.up.aiki.Rpc.out(envelop);
        }

        public static Envelop envelop(final IpcRequest request) {
            return io.spring.up.aiki.Rpc.inEnvelop(request);
        }

        public static Envelop envelop(final IpcResponse response) {
            return io.spring.up.aiki.Rpc.outEnvelop(response);
        }

        public static JsonObject json(final IpcRequest request) {
            return io.spring.up.aiki.Rpc.inJson(request);
        }

        public static JsonObject json(final IpcResponse response) {
            return io.spring.up.aiki.Rpc.outJson(response);
        }

        public static RpcClient getClient(final Channel channel) {
            return RpcClient.newInstance(channel);
        }
    }

    public static Optional<String> fetchLogin() {
        return Secure.getCurrentUserLogin();
    }

    public static String fetchRealName() {
        return Secure.getAuthorities().getString(Constants.REAL_NAME);
    }

    public static String fetchUserId() {
        return Secure.getAuthorities().getString(Constants.USER_ID);
    }

    public static String fetchRoleId() {
        return Secure.getAuthorities().getString(Constants.ROLE_ID);
    }

    public static String fetchRoleName() {
        return Secure.getAuthorities().getString(Constants.ROLE_NAME);
    }

    public static String fetchTenantId() {
        return Secure.getAuthorities().getString(Constants.TENANT_ID);
    }

    public static String fetchEmail() {
        return Secure.getAuthorities().getString(Constants.EMAIL);
    }

    public static String toJsonAuthority(final String literal) {
        final String content = new JsonObject(literal).encode();
        return Base64.getEncoder().encodeToString(content.getBytes(Charset.forName("UTF-8")));
    }

    public static String toJsonAuthority(final String userId,
                                         final String tenantId,
                                         final String roleName,
                                         final String roleId,
                                         final String email,
                                         final String realName) {
        final String content = new JsonObject()
                .put(Constants.USER_ID, userId)
                .put(Constants.ROLE_ID, roleId)
                .put(Constants.TENANT_ID, tenantId)
                .put(Constants.ROLE_NAME, roleName)
                .put(Constants.EMAIL, email)
                .put(Constants.REAL_NAME, realName).encode();
        return Base64.getEncoder().encodeToString(content.getBytes(Charset.forName("UTF-8")));
    }

    public static String toJsonAuthority(final String userId, final String roleName, final String roleId) {
        final String content = new JsonObject()
                .put(Constants.USER_ID, userId)
                .put(Constants.ROLE_ID, roleId)
                .put(Constants.ROLE_NAME, roleName).encode();
        return Base64.getEncoder().encodeToString(content.getBytes(Charset.forName("UTF-8")));
    }

    public static boolean inAuthoried() {
        return Secure.isAuthenticated();
    }

    public static boolean inRole(final String authority) {
        return Secure.isInRole(authority);
    }

    public static <T> Query<T> dsl(final JsonObject params) {
        return Query.<T>create(params);
    }

    public static Morphia morphia() {
        final Morphia morphia = new Morphia();
        final Converters converters = morphia.getMapper().getConverters();
        if (null != converters) {
            converters.addConverter(new JsonObjectConverter());
            converters.addConverter(new JsonArrayConverter());
        }
        return morphia;
    }

    public static boolean isScanned(final Class<?> clazz) {
        return Scanner.isValid(clazz);
    }
}
