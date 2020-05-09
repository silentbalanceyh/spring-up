package io.spring.up.boot;

import io.spring.up.boot.resolver.JsonBodyResolver;
import io.spring.up.boot.resolver.JsonEntityResolver;
import io.spring.up.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ArgumentConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentConfig.class);

    @Override
    public void addArgumentResolvers(
            final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonBodyResolver());
        Log.up(LOGGER, "Resolver has been set : {0}", JsonBodyResolver.class.getName());
        argumentResolvers.add(new JsonEntityResolver());
        Log.up(LOGGER, "Resolver has been set : {0}", JsonEntityResolver.class.getName());
        argumentResolvers.forEach(item -> Log.updg(LOGGER, "Resolver: " + item.getClass()));

    }
}
