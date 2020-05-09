package io.spring.up.query.fetcher;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.util.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FetcherUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetcherUtil.class);

    public static <T> JsonObject searchData(final Supplier<List<T>> queryFun,
                                            final Supplier<Long> countFun) {
        final List<T> entities = queryFun.get();
        final Long count = countFun.get();
        final JsonObject result = new JsonObject();
        final JsonArray listData = Ut.serializeJson(entities);
        result.put("list", listData);
        result.put("count", count);
        return result;
    }

    public static <T> OrderSpecifier[] getOrderSpecifier(final Inquiry inquiry, final EntityPathBase<T> entity) {
        OrderSpecifier[] orderSpecifiers = new OrderSpecifier[0];
        if (null != inquiry.getSorter()) {
            final JsonObject sorter = inquiry.getSorter().toJson();
            final List<OrderSpecifier<?>> specifiers = new ArrayList<>();
            for (final String field : sorter.fieldNames()) {
                final Boolean isAsc = sorter.getBoolean(field);
                final Object path = Ut.field(entity, field);
                final OrderSpecifier<?> specifier = getOrderSpecifier(path, isAsc);
                if (null != specifier) {
                    specifiers.add(specifier);
                    Log.info(LOGGER, "[ UP ] [QE] Order By: field = {0}, asc = {1}", field, isAsc);
                }
            }
            orderSpecifiers = (specifiers.toArray(new OrderSpecifier[]{}));
        }
        return orderSpecifiers;
    }

    @SuppressWarnings("unchecked")
    private static <I extends Comparable> OrderSpecifier<I> getOrderSpecifier(final Object path, final boolean asc) {
        final Class<?> clazz = path.getClass();
        // StringPath类型的排序
        if (StringPath.class == clazz) {
            return asc ? (OrderSpecifier<I>) ((StringPath) path).asc() : (OrderSpecifier<I>) ((StringPath) path).desc();
        } else if (DateTimePath.class == clazz) {
            return asc ? (OrderSpecifier<I>) ((DateTimePath) path).asc() : (OrderSpecifier<I>) ((DateTimePath) path).desc();
        } else if (BooleanPath.class == clazz) {
            return asc ? (OrderSpecifier<I>) ((BooleanPath) path).asc() : (OrderSpecifier<I>) ((BooleanPath) path).desc();
        }
        return null;
    }
}
