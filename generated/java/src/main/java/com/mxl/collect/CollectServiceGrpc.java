package com.mxl.collect;

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
    value = "by gRPC proto compiler (version 1.8.0)",
    comments = "Source: collect/collect.proto")
public final class CollectServiceGrpc {

  private CollectServiceGrpc() {}

  public static final String SERVICE_NAME = "collect.CollectService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCollectMethod()} instead. 
  public static final io.grpc.MethodDescriptor<com.mxl.collect.CollectRequest,
      com.mxl.collect.CollectResponse> METHOD_COLLECT = getCollectMethod();

  private static volatile io.grpc.MethodDescriptor<com.mxl.collect.CollectRequest,
      com.mxl.collect.CollectResponse> getCollectMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<com.mxl.collect.CollectRequest,
      com.mxl.collect.CollectResponse> getCollectMethod() {
    io.grpc.MethodDescriptor<com.mxl.collect.CollectRequest, com.mxl.collect.CollectResponse> getCollectMethod;
    if ((getCollectMethod = CollectServiceGrpc.getCollectMethod) == null) {
      synchronized (CollectServiceGrpc.class) {
        if ((getCollectMethod = CollectServiceGrpc.getCollectMethod) == null) {
          CollectServiceGrpc.getCollectMethod = getCollectMethod = 
              io.grpc.MethodDescriptor.<com.mxl.collect.CollectRequest, com.mxl.collect.CollectResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "collect.CollectService", "Collect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mxl.collect.CollectRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mxl.collect.CollectResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new CollectServiceMethodDescriptorSupplier("Collect"))
                  .build();
          }
        }
     }
     return getCollectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CollectServiceStub newStub(io.grpc.Channel channel) {
    return new CollectServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CollectServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CollectServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CollectServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CollectServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CollectServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 收藏文献
     * </pre>
     */
    public void collect(com.mxl.collect.CollectRequest request,
        io.grpc.stub.StreamObserver<com.mxl.collect.CollectResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCollectMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCollectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.mxl.collect.CollectRequest,
                com.mxl.collect.CollectResponse>(
                  this, METHODID_COLLECT)))
          .build();
    }
  }

  /**
   */
  public static final class CollectServiceStub extends io.grpc.stub.AbstractStub<CollectServiceStub> {
    private CollectServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CollectServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollectServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CollectServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 收藏文献
     * </pre>
     */
    public void collect(com.mxl.collect.CollectRequest request,
        io.grpc.stub.StreamObserver<com.mxl.collect.CollectResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCollectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CollectServiceBlockingStub extends io.grpc.stub.AbstractStub<CollectServiceBlockingStub> {
    private CollectServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CollectServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollectServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CollectServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 收藏文献
     * </pre>
     */
    public com.mxl.collect.CollectResponse collect(com.mxl.collect.CollectRequest request) {
      return blockingUnaryCall(
          getChannel(), getCollectMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CollectServiceFutureStub extends io.grpc.stub.AbstractStub<CollectServiceFutureStub> {
    private CollectServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CollectServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollectServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CollectServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 收藏文献
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mxl.collect.CollectResponse> collect(
        com.mxl.collect.CollectRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCollectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COLLECT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CollectServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CollectServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COLLECT:
          serviceImpl.collect((com.mxl.collect.CollectRequest) request,
              (io.grpc.stub.StreamObserver<com.mxl.collect.CollectResponse>) responseObserver);
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

  private static abstract class CollectServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CollectServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mxl.collect.Collect.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CollectService");
    }
  }

  private static final class CollectServiceFileDescriptorSupplier
      extends CollectServiceBaseDescriptorSupplier {
    CollectServiceFileDescriptorSupplier() {}
  }

  private static final class CollectServiceMethodDescriptorSupplier
      extends CollectServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CollectServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CollectServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CollectServiceFileDescriptorSupplier())
              .addMethod(getCollectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
