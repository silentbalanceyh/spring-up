// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.envelop.proto

package io.spring.up.ipc.model;

public final class UpEnvelop {
  private UpEnvelop() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_spring_up_ipc_model_IpcContent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_spring_up_ipc_model_IpcContent_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_spring_up_ipc_model_IpcEnvelop_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_spring_up_ipc_model_IpcEnvelop_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_spring_up_ipc_model_IpcRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_spring_up_ipc_model_IpcRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_spring_up_ipc_model_IpcResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_spring_up_ipc_model_IpcResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022zero.envelop.proto\022\026io.spring.up.ipc.m" +
      "odel\032\017zero.enum.proto\032\021zero.status.proto" +
      "\"\033\n\nIpcContent\022\r\n\005value\030\001 \001(\t\"i\n\nIpcEnve" +
      "lop\022/\n\004type\030\001 \001(\0162!.io.spring.up.ipc.mod" +
      "el.em.Format\022\014\n\004body\030\002 \001(\t\022\016\n\006stream\030\003 \001" +
      "(\014\022\014\n\004name\030\004 \001(\t\"\371\002\n\nIpcRequest\022:\n\017respo" +
      "nse_format\030\001 \001(\0162!.io.spring.up.ipc.mode" +
      "l.em.Format\022>\n\021response_category\030\002 \001(\0162#" +
      ".io.spring.up.ipc.model.em.Category\022\025\n\rr" +
      "esponse_size\030\003 \001(\005\0223\n\007envelop\030\004 \001(\0132\".io" +
      ".spring.up.ipc.model.IpcEnvelop\022\024\n\014is_cl" +
      "ient_id\030\005 \001(\010\022\026\n\016is_oauth_scope\030\006 \001(\010\0229\n" +
      "\talgorithm\030\007 \001(\0162&.io.spring.up.ipc.mode" +
      "l.em.Compression\022:\n\017response_status\030\010 \001(" +
      "\0132!.io.spring.up.ipc.model.IpcStatus\"j\n\013" +
      "IpcResponse\0223\n\007envelop\030\001 \001(\0132\".io.spring" +
      ".up.ipc.model.IpcEnvelop\022\021\n\tclient_id\030\002 " +
      "\001(\t\022\023\n\013oauth_scope\030\003 \001(\tB%\n\026io.spring.up" +
      ".ipc.modelB\tUpEnvelopP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.spring.up.ipc.model.em.UpEnum.getDescriptor(),
          io.spring.up.ipc.model.UpStatus.getDescriptor(),
        });
    internal_static_io_spring_up_ipc_model_IpcContent_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_spring_up_ipc_model_IpcContent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_spring_up_ipc_model_IpcContent_descriptor,
        new java.lang.String[] { "Value", });
    internal_static_io_spring_up_ipc_model_IpcEnvelop_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_io_spring_up_ipc_model_IpcEnvelop_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_spring_up_ipc_model_IpcEnvelop_descriptor,
        new java.lang.String[] { "Type", "Body", "Stream", "Name", });
    internal_static_io_spring_up_ipc_model_IpcRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_io_spring_up_ipc_model_IpcRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_spring_up_ipc_model_IpcRequest_descriptor,
        new java.lang.String[] { "ResponseFormat", "ResponseCategory", "ResponseSize", "Envelop", "IsClientId", "IsOauthScope", "Algorithm", "ResponseStatus", });
    internal_static_io_spring_up_ipc_model_IpcResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_io_spring_up_ipc_model_IpcResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_spring_up_ipc_model_IpcResponse_descriptor,
        new java.lang.String[] { "Envelop", "ClientId", "OauthScope", });
    io.spring.up.ipc.model.em.UpEnum.getDescriptor();
    io.spring.up.ipc.model.UpStatus.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
