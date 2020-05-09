// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.status.proto

package io.spring.up.ipc.model;

public interface IpcStatusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.spring.up.ipc.model.IpcStatus)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Status Code
   * </pre>
   *
   * <code>int32 code = 1;</code>
   * @return The code.
   */
  int getCode();

  /**
   * <pre>
   * Status Content
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * Status Content
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
