package io.spring.up.ipc.service;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: zero.def.service.proto")
public final class UnityServiceGrpc {

  private UnityServiceGrpc() {}

  public static final String SERVICE_NAME = "io.spring.up.ipc.service.UnityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.spring.up.ipc.model.IpcRequest,
      io.spring.up.ipc.model.IpcResponse> getUnityCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnityCall",
      requestType = io.spring.up.ipc.model.IpcRequest.class,
      responseType = io.spring.up.ipc.model.IpcResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.spring.up.ipc.model.IpcRequest,
      io.spring.up.ipc.model.IpcResponse> getUnityCallMethod() {
    io.grpc.MethodDescriptor<io.spring.up.ipc.model.IpcRequest, io.spring.up.ipc.model.IpcResponse> getUnityCallMethod;
    if ((getUnityCallMethod = UnityServiceGrpc.getUnityCallMethod) == null) {
      synchronized (UnityServiceGrpc.class) {
        if ((getUnityCallMethod = UnityServiceGrpc.getUnityCallMethod) == null) {
          UnityServiceGrpc.getUnityCallMethod = getUnityCallMethod =
              io.grpc.MethodDescriptor.<io.spring.up.ipc.model.IpcRequest, io.spring.up.ipc.model.IpcResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnityCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.IpcRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.IpcResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UnityServiceMethodDescriptorSupplier("UnityCall"))
              .build();
        }
      }
    }
    return getUnityCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UnityServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnityServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnityServiceStub>() {
        @java.lang.Override
        public UnityServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnityServiceStub(channel, callOptions);
        }
      };
    return UnityServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UnityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnityServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnityServiceBlockingStub>() {
        @java.lang.Override
        public UnityServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnityServiceBlockingStub(channel, callOptions);
        }
      };
    return UnityServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UnityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnityServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnityServiceFutureStub>() {
        @java.lang.Override
        public UnityServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnityServiceFutureStub(channel, callOptions);
        }
      };
    return UnityServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UnityServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Direct: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public void unityCall(io.spring.up.ipc.model.IpcRequest request,
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.IpcResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUnityCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUnityCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.spring.up.ipc.model.IpcRequest,
                io.spring.up.ipc.model.IpcResponse>(
                  this, METHODID_UNITY_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class UnityServiceStub extends io.grpc.stub.AbstractAsyncStub<UnityServiceStub> {
    private UnityServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnityServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnityServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Direct: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public void unityCall(io.spring.up.ipc.model.IpcRequest request,
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.IpcResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnityCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UnityServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UnityServiceBlockingStub> {
    private UnityServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnityServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnityServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Direct: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public io.spring.up.ipc.model.IpcResponse unityCall(io.spring.up.ipc.model.IpcRequest request) {
      return blockingUnaryCall(
          getChannel(), getUnityCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UnityServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UnityServiceFutureStub> {
    private UnityServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnityServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnityServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Direct: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.spring.up.ipc.model.IpcResponse> unityCall(
        io.spring.up.ipc.model.IpcRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUnityCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UNITY_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UnityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UnityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UNITY_CALL:
          serviceImpl.unityCall((io.spring.up.ipc.model.IpcRequest) request,
              (io.grpc.stub.StreamObserver<io.spring.up.ipc.model.IpcResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UnityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UnityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.spring.up.ipc.service.UpIpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UnityService");
    }
  }

  private static final class UnityServiceFileDescriptorSupplier
      extends UnityServiceBaseDescriptorSupplier {
    UnityServiceFileDescriptorSupplier() {}
  }

  private static final class UnityServiceMethodDescriptorSupplier
      extends UnityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UnityServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UnityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UnityServiceFileDescriptorSupplier())
              .addMethod(getUnityCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
