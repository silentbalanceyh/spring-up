package io.spring.up.core.rules;

import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<String, JsonObject> RULE_MAP =
            new ConcurrentHashMap<>();

    ConcurrentMap<String, Rule> RULE_REF_MAP = new ConcurrentHashMap<String, Rule>() {
        {
            this.put("required", new RequiredRule());
            this.put("length", new LengthRule());
            this.put("forbidden", new ForbiddenRule());
            this.put("array", new ArrayRule());
        }
    };
}
