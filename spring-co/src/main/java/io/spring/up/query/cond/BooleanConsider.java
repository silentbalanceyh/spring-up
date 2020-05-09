package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import io.vertx.up.atom.query.Inquiry;

public class BooleanConsider implements Consider {
    private transient final BooleanPath path;

    BooleanConsider(final Object path) {
        this.path = (BooleanPath) path;
    }

    @Override
    @SuppressWarnings("all")
    public BooleanExpression operator(final String op, final Object value) {
        BooleanExpression predicate = null;
        switch (op) {
            case Inquiry.Op.NEQ:
                predicate = path.ne(Boolean.valueOf(value.toString()));
                break;
            case Inquiry.Op.EQ:
                predicate = path.eq(Boolean.valueOf(value.toString()));
                break;
            case Inquiry.Op.FALSE:
                predicate = path.isFalse();
                break;
            case Inquiry.Op.TRUE:
                predicate = path.isTrue();
                break;
        }
        return predicate;
    }
}
