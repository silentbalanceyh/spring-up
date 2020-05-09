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
public final class ProduceServiceGrpc {

  private ProduceServiceGrpc() {}

  public static final String SERVICE_NAME = "io.spring.up.ipc.service.ProduceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest,
      io.spring.up.ipc.model.StreamClientResponse> getInputCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InputCall",
      requestType = io.spring.up.ipc.model.StreamClientRequest.class,
      responseType = io.spring.up.ipc.model.StreamClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest,
      io.spring.up.ipc.model.StreamClientResponse> getInputCallMethod() {
    io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest, io.spring.up.ipc.model.StreamClientResponse> getInputCallMethod;
    if ((getInputCallMethod = ProduceServiceGrpc.getInputCallMethod) == null) {
      synchronized (ProduceServiceGrpc.class) {
        if ((getInputCallMethod = ProduceServiceGrpc.getInputCallMethod) == null) {
          ProduceServiceGrpc.getInputCallMethod = getInputCallMethod =
              io.grpc.MethodDescriptor.<io.spring.up.ipc.model.StreamClientRequest, io.spring.up.ipc.model.StreamClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InputCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamClientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProduceServiceMethodDescriptorSupplier("InputCall"))
              .build();
        }
      }
    }
    return getInputCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProduceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProduceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProduceServiceStub>() {
        @java.lang.Override
        public ProduceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProduceServiceStub(channel, callOptions);
        }
      };
    return ProduceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProduceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProduceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProduceServiceBlockingStub>() {
        @java.lang.Override
        public ProduceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProduceServiceBlockingStub(channel, callOptions);
        }
      };
    return ProduceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProduceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProduceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProduceServiceFutureStub>() {
        @java.lang.Override
        public ProduceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProduceServiceFutureStub(channel, callOptions);
        }
      };
    return ProduceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ProduceServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Client -&gt; Server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientRequest> inputCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getInputCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInputCallMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                io.spring.up.ipc.model.StreamClientRequest,
                io.spring.up.ipc.model.StreamClientResponse>(
                  this, METHODID_INPUT_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class ProduceServiceStub extends io.grpc.stub.AbstractAsyncStub<ProduceServiceStub> {
    private ProduceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProduceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProduceServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client -&gt; Server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientRequest> inputCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getInputCallMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ProduceServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProduceServiceBlockingStub> {
    private ProduceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProduceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProduceServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ProduceServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ProduceServiceFutureStub> {
    private ProduceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProduceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProduceServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_INPUT_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProduceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProduceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INPUT_CALL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.inputCall(
              (io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProduceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProduceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.spring.up.ipc.service.UpIpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProduceService");
    }
  }

  private static final class ProduceServiceFileDescriptorSupplier
      extends ProduceServiceBaseDescriptorSupplier {
    ProduceServiceFileDescriptorSupplier() {}
  }

  private static final class ProduceServiceMethodDescriptorSupplier
      extends ProduceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProduceServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ProduceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProduceServiceFileDescriptorSupplier())
              .addMethod(getInputCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
