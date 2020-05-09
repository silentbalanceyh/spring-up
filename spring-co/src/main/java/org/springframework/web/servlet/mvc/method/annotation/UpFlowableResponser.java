package org.springframework.web.servlet.mvc.method.annotation;

import io.spring.up.boot.converter.Responser;
import io.spring.up.log.Log;
import io.spring.up.model.Pagination;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;

public class UpFlowableResponser implements Responser {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpFlowableResponser.class);

    public static Class<?> FLOWABLE_CLS = ReactiveTypeHandler.CollectedValuesList.class;

    @Override
    public JsonObject process(final Object pagination) {
        // 过滤null元素
        final ReactiveTypeHandler.CollectedValuesList valuesList =
                (ReactiveTypeHandler.CollectedValuesList) pagination;
        // 查看该集合的相关信息
        if (0 == valuesList.size()) {
            // 没有任何元素
            return new JsonObject().put("count", 0).put("list", new JsonArray());
        } else {
            final ResolvableType returnType = valuesList.getReturnType();
            Log.updg(LOGGER, "ReturnType in valuesList: {0}, class = {1}", returnType, returnType.getClass());
            Log.updg(LOGGER, "ComponentType in valuesList: {0}, class = {1}", returnType.getComponentType(), returnType.getComponentType().getClass());
            if (1 == valuesList.size()) {
                final Object element = valuesList.get(Values.IDX);
                if (element.getClass() == Pagination.class) {
                    return Responser.get(Pagination.class).process(valuesList.get(Values.IDX));
                } else {
                    return Responser.get(Object.class).process(valuesList);
                }
            } else {
                // 其他照旧
                return Responser.get(Object.class).process(valuesList);
            }
        }
    }
}
