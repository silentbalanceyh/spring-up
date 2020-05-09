package io.spring.up.exception.internal;

interface Message {

    String EMPTY_STREAM = "Empty Stream found when read file {0}.";

    String YAML_FORMAT = "Yaml data file {0} format is invalid.";

    String ECODE_MISSING = "The code = {0} of error is missing in your file: application-error.yml.";
}
