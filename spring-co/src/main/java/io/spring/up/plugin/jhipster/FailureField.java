package io.spring.up.plugin.jhipster;

import java.io.Serializable;

public class FailureField implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String objectName;

    private final String field;

    private final String message;

    public FailureField(final String dto, final String field, final String message) {
        this.objectName = dto;
        this.field = field;
        this.message = message;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }
}
