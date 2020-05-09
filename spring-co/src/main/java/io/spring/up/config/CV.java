package io.spring.up.config;

import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    /**
     * 读取器
     */
    ConcurrentMap<String, Node<JsonObject>> REFERENCES
            = new ConcurrentHashMap<>();
    /**
     * 配置数据信息
     */
    ConcurrentMap<String, JsonObject> CONFIG
            = new ConcurrentHashMap<>();
    ConcurrentMap<String, JsonObject> INTERNAL
            = new ConcurrentHashMap<>();
}
