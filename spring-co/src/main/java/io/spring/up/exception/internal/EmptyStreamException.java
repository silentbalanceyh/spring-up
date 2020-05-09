package io.spring.up.exception.internal;

import io.spring.up.exception.InternalException;

import java.text.MessageFormat;

public class EmptyStreamException extends InternalException {

    public EmptyStreamException(final String message) {
        super(MessageFormat.format(Message.EMPTY_STREAM, message), -10003);
    }

    @Override
    public int getCode() {
        return -10003;
    }
}
