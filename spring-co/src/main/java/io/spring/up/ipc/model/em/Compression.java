// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: zero.enum.proto

package io.spring.up.ipc.model.em;

/**
 * <pre>
 * Compress Algorithm
 * </pre>
 *
 * Protobuf enum {@code io.spring.up.ipc.model.em.Compression}
 */
public enum Compression
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * No compression
   * </pre>
   *
   * <code>NONE = 0;</code>
   */
  NONE(0),
  /**
   * <pre>
   * Gzip compression
   * </pre>
   *
   * <code>GZIP = 1;</code>
   */
  GZIP(1),
  /**
   * <pre>
   * Deflate compression
   * </pre>
   *
   * <code>DEFLATE = 2;</code>
   */
  DEFLATE(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * No compression
   * </pre>
   *
   * <code>NONE = 0;</code>
   */
  public static final int NONE_VALUE = 0;
  /**
   * <pre>
   * Gzip compression
   * </pre>
   *
   * <code>GZIP = 1;</code>
   */
  public static final int GZIP_VALUE = 1;
  /**
   * <pre>
   * Deflate compression
   * </pre>
   *
   * <code>DEFLATE = 2;</code>
   */
  public static final int DEFLATE_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Compression valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static Compression forNumber(int value) {
    switch (value) {
      case 0: return NONE;
      case 1: return GZIP;
      case 2: return DEFLATE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Compression>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Compression> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Compression>() {
          public Compression findValueByNumber(int number) {
            return Compression.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return io.spring.up.ipc.model.em.UpEnum.getDescriptor().getEnumTypes().get(0);
  }

  private static final Compression[] VALUES = values();

  public static Compression valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Compression(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:io.spring.up.ipc.model.em.Compression)
}

