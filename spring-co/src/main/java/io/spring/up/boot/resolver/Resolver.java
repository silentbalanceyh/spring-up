package io.spring.up.boot.resolver;

import io.spring.up.aiki.Ux;
import io.spring.up.core.rules.Ruler;
import io.spring.up.cv.Encodings;
import io.spring.up.cv.Strings;
import io.spring.up.exception.internal.JsonDecodeException;
import io.spring.up.exception.web._400ParameterMissingException;
import io.spring.up.exception.web._500ParameterTypeException;
import io.spring.up.exception.web._500WebRequestIoException;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;

class Resolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Resolver.class);
    private static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";

    static String getRequestBody(final MethodParameter methodParameter,
                                 final NativeWebRequest request) {
        final HttpServletRequest servletRequest =
                request.getNativeRequest(HttpServletRequest.class);
        String jsonBody = (String) servletRequest.getAttribute(JSON_REQUEST_BODY);
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getInputStream(), Encodings.CHARSET);
                servletRequest.setAttribute(JSON_REQUEST_BODY, jsonBody);
            } catch (final IOException e) {
                throw new _500WebRequestIoException(methodParameter.getMethod().getDeclaringClass(), e);
            }
        }
        return jsonBody;
    }

    static String resolvePath(final String folder, final String value) {
        final String path = folder + Strings.SLASH + value;
        return path.replaceAll("/+", "/");
    }

    static Object resolveJson(final Class<?> clazz, final String body) {
        return Fn.getJvm(null, () -> {
            try {
                if (body.trim().startsWith(Strings.LEFT_BRACE)) {
                    final JsonObject resolved = new JsonObject(body);
                    return Ux.inKey(resolved);
                }
                if (body.trim().startsWith(Strings.LEFT_SQ_BRACKET)) {
                    final JsonArray resolved = new JsonArray();
                    return Ux.inKey(resolved);
                }
                return null;
            } catch (final JsonDecodeException ex) {
                if (body.trim().startsWith(Strings.LEFT_BRACE)) {
                    throw new _500ParameterTypeException(clazz, JsonObject.class);
                }
                if (body.trim().startsWith(Strings.LEFT_SQ_BRACKET)) {
                    throw new _500ParameterTypeException(clazz, JsonArray.class);
                }
                throw ex;
            }
        }, body);
    }

    static void verifyInput(final Class<?> clazz,
                            final Class<? extends Annotation> annotationType,
                            final MethodParameter methodParameter,
                            final Object reference) {
        final Annotation rule = methodParameter.getParameterAnnotation(annotationType);
        final boolean required = Ut.invoke(rule, "required");
        if (null == reference && required) {
            // 必填选项
            throw new _400ParameterMissingException(clazz,
                    methodParameter.getParameterName(),
                    methodParameter.getMethod().getName());
        } else {
            if (null != reference) {
                final String folder = Ut.invoke(rule, "folder");
                final String value = Ut.invoke(rule, "value");
                // 路径处理
                final String path = resolvePath(folder, value);
                Log.up(LOGGER, "Read rule file from {0}", path);
                if (Ut.isJArray(reference)) {
                    // 数组验证
                    Ruler.verify(path, (JsonArray) reference);
                } else if (Ut.isJObject(reference)) {
                    // 对象验证
                    Ruler.verify(path, (JsonObject) reference);
                }
            }
        }
    }
}
