package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Consider {

    BooleanExpression operator(String op, Object value);

    static Consider create(final Class<?> clazz, final Object path) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        if (StringPath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new StringConsider(path);
        } else if (NumberPath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new NumberConsider(path);
        } else if (EnumPath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new EnumConsider(path);
        } else if (BooleanPath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new BooleanConsider(path);
        } else if (SimplePath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new SimpleConsider(path);
        } else if (ListPath.class == clazz) {
            logger.debug("[ UP ] Selected: " + clazz);
            return new ListConsider(path);
        } else {
            logger.debug("[ UP ] Unmatch: " + clazz);
        }
        return null;
    }
}
