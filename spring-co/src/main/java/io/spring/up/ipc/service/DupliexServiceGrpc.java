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
public final class DupliexServiceGrpc {

  private DupliexServiceGrpc() {}

  public static final String SERVICE_NAME = "io.spring.up.ipc.service.DupliexService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest,
      io.spring.up.ipc.model.StreamServerResponse> getDupliexCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DupliexCall",
      requestType = io.spring.up.ipc.model.StreamClientRequest.class,
      responseType = io.spring.up.ipc.model.StreamServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest,
      io.spring.up.ipc.model.StreamServerResponse> getDupliexCallMethod() {
    io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamClientRequest, io.spring.up.ipc.model.StreamServerResponse> getDupliexCallMethod;
    if ((getDupliexCallMethod = DupliexServiceGrpc.getDupliexCallMethod) == null) {
      synchronized (DupliexServiceGrpc.class) {
        if ((getDupliexCallMethod = DupliexServiceGrpc.getDupliexCallMethod) == null) {
          DupliexServiceGrpc.getDupliexCallMethod = getDupliexCallMethod =
              io.grpc.MethodDescriptor.<io.spring.up.ipc.model.StreamClientRequest, io.spring.up.ipc.model.StreamServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DupliexCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamClientRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamServerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DupliexServiceMethodDescriptorSupplier("DupliexCall"))
              .build();
        }
      }
    }
    return getDupliexCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DupliexServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DupliexServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DupliexServiceStub>() {
        @java.lang.Override
        public DupliexServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DupliexServiceStub(channel, callOptions);
        }
      };
    return DupliexServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DupliexServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DupliexServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DupliexServiceBlockingStub>() {
        @java.lang.Override
        public DupliexServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DupliexServiceBlockingStub(channel, callOptions);
        }
      };
    return DupliexServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DupliexServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DupliexServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DupliexServiceFutureStub>() {
        @java.lang.Override
        public DupliexServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DupliexServiceFutureStub(channel, callOptions);
        }
      };
    return DupliexServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DupliexServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Full: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientRequest> dupliexCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getDupliexCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDupliexCallMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                io.spring.up.ipc.model.StreamClientRequest,
                io.spring.up.ipc.model.StreamServerResponse>(
                  this, METHODID_DUPLIEX_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class DupliexServiceStub extends io.grpc.stub.AbstractAsyncStub<DupliexServiceStub> {
    private DupliexServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DupliexServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DupliexServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Full: Client -&gt; Server -&gt; Client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamClientRequest> dupliexCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getDupliexCallMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class DupliexServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DupliexServiceBlockingStub> {
    private DupliexServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DupliexServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DupliexServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class DupliexServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DupliexServiceFutureStub> {
    private DupliexServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DupliexServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DupliexServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DUPLIEX_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DupliexServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DupliexServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_DUPLIEX_CALL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.dupliexCall(
              (io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DupliexServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DupliexServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.spring.up.ipc.service.UpIpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DupliexService");
    }
  }

  private static final class DupliexServiceFileDescriptorSupplier
      extends DupliexServiceBaseDescriptorSupplier {
    DupliexServiceFileDescriptorSupplier() {}
  }

  private static final class DupliexServiceMethodDescriptorSupplier
      extends DupliexServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DupliexServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DupliexServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DupliexServiceFileDescriptorSupplier())
              .addMethod(getDupliexCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
