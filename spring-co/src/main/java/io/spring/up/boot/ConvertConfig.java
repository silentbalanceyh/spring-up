package io.spring.up.boot;

import io.spring.up.boot.converter.JacksonConverter;
import io.spring.up.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConvertConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertConfig.class);

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converterList) {
        // Jackson2 serialization
        final JacksonConverter converter = new JacksonConverter();
        Log.up(LOGGER, "Converter has been set : {0}", JacksonConverter.class.getName());
        // 优先添加，并且替换原始的MappingJackson2HttpMessageConverter，只保留一种
        final List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(converter);
        /*
        converterList.forEach(item -> {
            if (null != item && MappingJackson2HttpMessageConverter.class != item.getClass()) {
                list.add(item);
            }
        });*/
        list.forEach(item -> Log.debug(LOGGER, "[ UP DG ] Converter: " + item.getClass()));
        // 清除后处理
        converterList.clear();
        converterList.addAll(list);
    }
}
