package io.spring.up.boot.converter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.TypeUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Type;

public class JacksonConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonConverter.class);
    private static final MediaType TEXT_EVENT_STREAM = new MediaType("text", "event-stream");
    private final PrettyPrinter ssePrettyPrinter;

    public JacksonConverter() {
        super(Ut.mapper());
        final DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));
        this.ssePrettyPrinter = prettyPrinter;
    }

    @Override
    public void writeInternal(@NotNull final Object object, final Type type, final HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

        final MediaType contentType = outputMessage.getHeaders().getContentType();
        final JsonEncoding encoding = this.getJsonEncoding(contentType);
        final JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
        // 要写异常，放到内部
        ObjectWriter objectWriter;
        try {

            Class<?> serializationView = null;
            FilterProvider filters = null;
            Object value = object;
            JavaType javaType = null;
            if (object instanceof MappingJacksonValue) {
                final MappingJacksonValue container = (MappingJacksonValue) object;
                value = container.getValue();
                serializationView = container.getSerializationView();
                filters = container.getFilters();
            }
            final Object dataValue = value;
            if (type != null && TypeUtils.isAssignable(type, dataValue.getClass())) {
                javaType = this.getJavaType(JsonObject.class, null);
            }

            if (serializationView != null) {
                objectWriter = this.objectMapper.writerWithView(serializationView);
            } else if (filters != null) {
                objectWriter = this.objectMapper.writer(filters);
            } else {
                objectWriter = this.objectMapper.writer();
            }
            if (javaType != null && javaType.isContainerType()) {
                objectWriter = objectWriter.forType(javaType);
            }
            final SerializationConfig config = objectWriter.getConfig();
            if (contentType != null && contentType.isCompatibleWith(TEXT_EVENT_STREAM) &&
                    config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
                objectWriter = objectWriter.with(this.ssePrettyPrinter);
            }
            // 转换成Json data节点
            final JsonObject data = this.extractData(dataValue);
            // 日志监控
            final Boolean isClosed = generator.isClosed();
            if (isClosed) {
                Log.warn(LOGGER, "Message: {3}, isClosed: {0}, Object: {1} Type: {2}.",
                        isClosed, String.valueOf(object.hashCode()),
                        null != type ? String.valueOf(type.hashCode()) : null,
                        String.valueOf(outputMessage.hashCode()));
            } else {
                Log.updg(LOGGER, "Message: {3}, isClosed: {0}, Object: {1} Type: {2}.",
                        isClosed, String.valueOf(object.hashCode()),
                        null != type ? String.valueOf(type.hashCode()) : null,
                        String.valueOf(outputMessage.hashCode()));
            }
            this.writePrefix(generator, data);
            objectWriter.writeValue(generator, data);
            super.writeSuffix(generator, data);
        } catch (final JsonProcessingException ex) {
            // TODO: Debug调试用
            ex.printStackTrace();
        } catch (final Throwable ex) {
            // TODO: Debug调试用
            ex.printStackTrace();
        }
    }

    private JsonObject extractData(final Object value) {
        JsonObject data = new JsonObject();
        if (null != value) {
            // data节点
            final Responser responser = Responser.get(value.getClass());
            data = responser.process(value);
        }
        Log.up(LOGGER, "Response Data: {0}", data.encode());
        // 解决Spring中的兼容性问题
        return data;
    }
}
