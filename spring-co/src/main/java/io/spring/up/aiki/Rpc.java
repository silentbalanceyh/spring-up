package io.spring.up.aiki;

import io.spring.up.ipc.model.IpcEnvelop;
import io.spring.up.ipc.model.IpcRequest;
import io.spring.up.ipc.model.IpcResponse;
import io.spring.up.model.Envelop;
import io.vertx.core.json.JsonObject;

class Rpc {

    static IpcRequest in(final JsonObject data) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder().setBody(data.encode()).build();
        return IpcRequest.newBuilder().setEnvelop(envelop).build();
    }

    static IpcRequest in(final Envelop in) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder().setBody(in.json().encode()).build();
        return IpcRequest.newBuilder().setEnvelop(envelop).build();
    }

    static IpcResponse out(final JsonObject data) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder().setBody(data.encode()).build();
        return IpcResponse.newBuilder().setEnvelop(envelop).build();
    }

    static IpcResponse out(final Envelop data) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder().setBody(data.json().encode()).build();
        return IpcResponse.newBuilder().setEnvelop(envelop).build();
    }

    static Envelop inEnvelop(final IpcRequest request) {
        final IpcEnvelop envelop = request.getEnvelop();
        return Envelop.success(new JsonObject(envelop.getBody()));
    }

    static JsonObject inJson(final IpcRequest request) {
        final IpcEnvelop envelop = request.getEnvelop();
        return new JsonObject(envelop.getBody());
    }

    static Envelop outEnvelop(final IpcResponse response) {
        final IpcEnvelop envelop = response.getEnvelop();
        return Envelop.success(new JsonObject(envelop.getBody()));
    }

    static JsonObject outJson(final IpcResponse response) {
        final IpcEnvelop envelop = response.getEnvelop();
        return new JsonObject(envelop.getBody());
    }
}
