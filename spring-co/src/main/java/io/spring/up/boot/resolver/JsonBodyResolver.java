package io.spring.up.boot.resolver;

import io.spring.up.annotations.JsonBody;
import io.spring.up.exception.web._500ParameterTypeException;
import io.spring.up.log.Log;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonBodyResolver implements HandlerMethodArgumentResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonBodyResolver.class);

    @Override
    public boolean supportsParameter(final MethodParameter methodParameter) {
        Boolean isMatch = methodParameter.hasParameterAnnotation(JsonBody.class);
        if (isMatch) {
            // 不是JsonObject或JsonArray，类型定义错
            isMatch = Ut.isJObject(methodParameter.getParameterType()) ||
                    Ut.isJArray(methodParameter.getParameterType());
            if (!isMatch) {
                throw new _500ParameterTypeException(this.getClass(), methodParameter.getParameterType());
            }
        }
        Log.up(LOGGER, "Resolver match result {0} = {1}", this.getClass().getName(),
                isMatch.toString());
        return isMatch;
    }

    @Override
    public Object resolveArgument(final MethodParameter methodParameter,
                                  final ModelAndViewContainer modelAndViewContainer,
                                  final NativeWebRequest nativeWebRequest,
                                  final WebDataBinderFactory webDataBinderFactory) throws Exception {
        final String body = Resolver.getRequestBody(methodParameter, nativeWebRequest);
        // 解析参数到固定格式，可支持为空相关计算
        final Object reference = Resolver.resolveJson(this.getClass(), body);
        // 规则基础验证处理
        Resolver.verifyInput(this.getClass(), JsonBody.class, methodParameter, reference);

        return reference;
    }
}
