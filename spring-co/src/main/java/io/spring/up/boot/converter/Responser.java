package io.spring.up.boot.converter;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.spring.up.model.Pagination;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 响应专用数据处理
 */
public interface Responser {
    /**
     * 处理响应方法
     *
     * @param value 目前的响应数据
     * @return 生成新的数据格式
     */
    JsonObject process(final Object value);

    ConcurrentMap<Class<?>, Responser> PROCESSOR =
            new ConcurrentHashMap<Class<?>, Responser>() {
                {
                    this.put(JsonObject.class, Ut.singleton(JObjectResponser.class));
                    this.put(JsonArray.class, Ut.singleton(JArrayResponser.class));
                    this.put(Object.class, Ut.singleton(CommonResponser.class));
                    this.put(Single.class, Ut.singleton(RxSingleResponser.class));
                    this.put(Flowable.class, Ut.singleton(RxFlowableResponser.class));
                    this.put(String.class, Ut.singleton(StringResponser.class));
                    this.put(StringBuffer.class, Ut.singleton(StringResponser.class));
                    this.put(StringBuilder.class, Ut.singleton(StringResponser.class));
                    this.put(Pagination.class, Ut.singleton(PaginationResponser.class));
                    // Hooker
                    // this.put(UpFlowableResponser.FLOWABLE_CLS, Ut.singleton(UpFlowableResponser.class));
                }
            };

    static Responser get(final Class<?> clazz) {
        if (Single.class.isAssignableFrom(clazz)) {
            return PROCESSOR.get(Single.class);
        } else if (Flowable.class.isAssignableFrom(clazz)) {
            return PROCESSOR.get(Flowable.class);
        } else if (PROCESSOR.containsKey(clazz)) {
            return PROCESSOR.get(clazz);
        } else {
            // 原始类型
            if (Ut.isPrimary(clazz)) {
                return PROCESSOR.get(String.class);
            } else {
                return PROCESSOR.get(Object.class);
            }
        }
    }
}
