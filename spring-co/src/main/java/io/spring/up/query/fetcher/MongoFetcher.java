package io.spring.up.query.fetcher;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import io.spring.up.log.Log;
import io.spring.up.query.Fetcher;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.query.Inquiry;
import io.vertx.up.atom.query.Pager;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MongoFetcher<T> implements Fetcher<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoFetcher.class);
    private transient Inquiry inquiry;
    private transient EntityPathBase<T> entity;
    private final transient Morphia morphia;
    private final transient Datastore datastore;

    public MongoFetcher(final Morphia morphia, final Datastore dataStore) {
        this.morphia = morphia;
        this.datastore = dataStore;
    }

    @Override
    public JsonObject search(final Predicate predicate) {
        return FetcherUtil.searchData(
                () -> this.searchAdvanced(predicate),
                () -> this.countAvanced(predicate));
    }

    @Override
    public List<T> searchList(final Predicate predicate) {
        return this.searchAdvanced(predicate);
    }

    @Override
    public Long count(final Predicate predicate) {
        return this.countAvanced(predicate);
    }

    private List<T> searchAdvanced(final Predicate predicate) {
        MorphiaQuery<T> query = new MorphiaQuery<>(this.morphia, this.datastore, this.entity);
        Log.info(LOGGER, "[ UP ] [QE] Criteria = {0}", null == predicate ? null : predicate.toString());
        // 条件处理
        if (null != predicate) {
            query = query.where(predicate);
        }
        // 排序处理
        query = this.getOrderBy(query);
        // 分页处理
        query = this.getPager(query);
        return query.fetch();
    }

    private Long countAvanced(final Predicate predicate) {
        MorphiaQuery<T> query = new MorphiaQuery<T>(this.morphia, this.datastore, this.entity);
        if (null != predicate) {
            query = query.where(predicate);
        }
        return query.fetchCount();
    }

    @Override
    public Fetcher bind(final EntityPathBase<T> entity) {
        this.entity = entity;
        return this;
    }

    @Override
    public Fetcher bind(final Inquiry inquiry) {
        this.inquiry = inquiry;
        return this;
    }

    private MorphiaQuery<T> getPager(final MorphiaQuery<T> query) {
        MorphiaQuery<T> result = query;
        if (null != this.inquiry.getPager()) {
            final Pager pager = this.inquiry.getPager();
            Log.info(LOGGER, "[ UP ] [QE] Pagination: start = {0}, size = {1}", pager.getStart(), pager.getSize());
            result = result.limit(pager.getSize()).offset(pager.getStart());
        }
        return result;
    }


    /**
     * 排序
     *
     * @param query
     * @return
     */
    private MorphiaQuery<T> getOrderBy(final MorphiaQuery<T> query) {
        MorphiaQuery<T> result = query;
        final OrderSpecifier[] orderSpecifiers = FetcherUtil.getOrderSpecifier(this.inquiry, this.entity);
        if (0 < orderSpecifiers.length) {
            result = result.orderBy(orderSpecifiers);
        }
        return result;
    }

}
