package io.spring.up.exception.internal;

import io.spring.up.exception.InternalException;

import java.text.MessageFormat;

public class YamlFormatException extends InternalException {

    public YamlFormatException(final String filename) {
        super(MessageFormat.format(Message.YAML_FORMAT, filename), -10004);
    }

    @Override
    public int getCode() {
        return -10004;
    }
}
