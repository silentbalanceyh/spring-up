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
public final class ConsumeServiceGrpc {

  private ConsumeServiceGrpc() {}

  public static final String SERVICE_NAME = "io.spring.up.ipc.service.ConsumeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamServerRequest,
      io.spring.up.ipc.model.StreamServerResponse> getOutputCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OutputCall",
      requestType = io.spring.up.ipc.model.StreamServerRequest.class,
      responseType = io.spring.up.ipc.model.StreamServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamServerRequest,
      io.spring.up.ipc.model.StreamServerResponse> getOutputCallMethod() {
    io.grpc.MethodDescriptor<io.spring.up.ipc.model.StreamServerRequest, io.spring.up.ipc.model.StreamServerResponse> getOutputCallMethod;
    if ((getOutputCallMethod = ConsumeServiceGrpc.getOutputCallMethod) == null) {
      synchronized (ConsumeServiceGrpc.class) {
        if ((getOutputCallMethod = ConsumeServiceGrpc.getOutputCallMethod) == null) {
          ConsumeServiceGrpc.getOutputCallMethod = getOutputCallMethod =
              io.grpc.MethodDescriptor.<io.spring.up.ipc.model.StreamServerRequest, io.spring.up.ipc.model.StreamServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OutputCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.spring.up.ipc.model.StreamServerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ConsumeServiceMethodDescriptorSupplier("OutputCall"))
              .build();
        }
      }
    }
    return getOutputCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ConsumeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceStub>() {
        @java.lang.Override
        public ConsumeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumeServiceStub(channel, callOptions);
        }
      };
    return ConsumeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ConsumeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceBlockingStub>() {
        @java.lang.Override
        public ConsumeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumeServiceBlockingStub(channel, callOptions);
        }
      };
    return ConsumeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ConsumeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ConsumeServiceFutureStub>() {
        @java.lang.Override
        public ConsumeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ConsumeServiceFutureStub(channel, callOptions);
        }
      };
    return ConsumeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ConsumeServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Server -&gt; Client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerRequest> outputCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getOutputCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOutputCallMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                io.spring.up.ipc.model.StreamServerRequest,
                io.spring.up.ipc.model.StreamServerResponse>(
                  this, METHODID_OUTPUT_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class ConsumeServiceStub extends io.grpc.stub.AbstractAsyncStub<ConsumeServiceStub> {
    private ConsumeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Server -&gt; Client
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerRequest> outputCall(
        io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getOutputCallMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ConsumeServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ConsumeServiceBlockingStub> {
    private ConsumeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumeServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class ConsumeServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ConsumeServiceFutureStub> {
    private ConsumeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ConsumeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ConsumeServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_OUTPUT_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ConsumeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ConsumeServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_OUTPUT_CALL:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.outputCall(
              (io.grpc.stub.StreamObserver<io.spring.up.ipc.model.StreamServerResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ConsumeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ConsumeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.spring.up.ipc.service.UpIpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ConsumeService");
    }
  }

  private static final class ConsumeServiceFileDescriptorSupplier
      extends ConsumeServiceBaseDescriptorSupplier {
    ConsumeServiceFileDescriptorSupplier() {}
  }

  private static final class ConsumeServiceMethodDescriptorSupplier
      extends ConsumeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ConsumeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ConsumeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ConsumeServiceFileDescriptorSupplier())
              .addMethod(getOutputCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
