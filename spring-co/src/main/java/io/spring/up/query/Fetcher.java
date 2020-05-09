package io.spring.up.query;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Inquiry;

import java.util.List;

public interface Fetcher<T> {

    JsonObject search(final Predicate predicate);

    List<T> searchList(final Predicate predicate);

    Long count(final Predicate predicate);

    Fetcher bind(EntityPathBase<T> entityCls);

    Fetcher bind(Inquiry inquiry);
}
