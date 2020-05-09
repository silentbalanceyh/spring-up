package io.spring.up.core.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.spring.up.exception.WebException;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.text.MessageFormat;

/**
 * 验证规则专用引擎，用于验证Api接口相关数据信息
 */
public class Ruler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ruler.class);
    /**
     * 专用于根路径查找
     */
    private static final String ROOT = "rules/{0}.yml";

    private static final ObjectMapper YAML = new YAMLMapper();

    /**
     * 生成最终访问的yml文件
     *
     * @param path
     * @return
     */
    private static JsonObject getConfig(final String path) {
        final String filename = MessageFormat.format(ROOT, path).replaceAll("/+", "/");
        // 池化处理，防止多次加载配置文件
        return Fn.pool(Pool.RULE_MAP, filename, () -> Fn.getJvm(() -> {
            Log.debug(LOGGER, "[ UP ] (IO) filename = {0}, path = {1}", filename, path);
            final ClassPathResource resource = new ClassPathResource(filename);
            final InputStream in = resource.getInputStream();
            final JsonNode json = YAML.readTree(in);
            return null == json ? new JsonObject() : new JsonObject(json.toString());
        }));
    }

    public static void verify(final String file, final JsonObject data) {
        final JsonObject config = getConfig(file);
        verify(config, data);
    }

    public static void verify(final String file, final JsonArray data) {
        final JsonObject config = getConfig(file);
        Ut.itJArray(data, (item, index) -> verify(config, item));
    }

    private static void verify(final String name, final Object value, final JsonArray rules) {
        Ut.itJArray(rules, (item, index) -> verify(name, value, item));
    }

    private static void verify(final String name, final Object value, final JsonObject rule) {
        final String type = Fn.getNull(() -> rule.getString("type"), rule);
        final JsonObject config = Fn.getNull(new JsonObject(), () -> rule.getJsonObject("config"), rule);
        Fn.safeNull(() -> {
            final Rule ruler = Rule.get(type);
            Fn.safeNull(() -> {
                Log.debug(LOGGER, "[ UP DG ] Field = {0}, Value = {1}, Config = {2}, Ruler = {3}",
                        name, value, config, ruler.getClass().getName());
                final WebException error = ruler.verify(name, value, config);
                Fn.safeNull(() -> {
                    final String message = Fn.getNull(() -> rule.getString("message"), rule);
                    if (null != message) {
                        error.setInfo(message);
                        throw error;
                    }
                }, error);
            }, ruler);
        }, type);
    }

    private static void verify(
            final JsonObject config,
            final JsonObject data) {
        Log.debug(LOGGER, "[ UP DG ] Rule = {0}, Data = {1}",
                config, data);
        // 必须参数的验证
        Fn.safeNull(() -> Ut.itJObject(config, (configItem, field) ->
                Fn.safeNull(() -> Ut.itJArray((JsonArray) configItem,
                        (itemJson, index) -> verify(field, data.getValue(field), itemJson)), configItem)
        ), config);
    }
}