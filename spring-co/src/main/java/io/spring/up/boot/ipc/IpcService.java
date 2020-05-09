package io.spring.up.boot.ipc;

import io.grpc.stub.StreamObserver;
import io.spring.up.aiki.Ux;
import io.spring.up.cv.Constants;
import io.spring.up.exception.WebException;
import io.spring.up.ipc.core.IpcSelector;
import io.spring.up.ipc.model.IpcRequest;
import io.spring.up.ipc.model.IpcResponse;
import io.spring.up.ipc.service.UnityServiceGrpc;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @GrpcService(UnityServiceGrpc.class)
@GrpcService
public class IpcService extends UnityServiceGrpc.UnityServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpcService.class);

    @Override
    public void unityCall(final IpcRequest request,
                          final StreamObserver<IpcResponse> responseObserver) {
        final JsonObject envelop = Ux.Rpc.json(request);
        final String address = envelop.getString(Constants.ADDRESS);
        final JsonObject data = envelop.getJsonObject(Constants.DATA);
        final JsonObject result = new JsonObject();
        try {
            final JsonObject invoked = IpcSelector.init(address).invoke(data);
            result.mergeIn(invoked, true);
            responseObserver.onNext(Ux.Rpc.response(result));
        } catch (final WebException ex) {
            Log.error(LOGGER, ex);
            result.mergeIn(ex.toJson(), true);
            responseObserver.onNext(Ux.Rpc.response(result));
        } catch (final Throwable ex) {
            Log.jvm(LOGGER, ex);
            responseObserver.onError(ex);
        } finally {
            responseObserver.onCompleted();
        }
    }
}
