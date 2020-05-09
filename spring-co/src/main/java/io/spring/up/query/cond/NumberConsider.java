package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import io.vertx.up.atom.query.Inquiry;

public class NumberConsider implements Consider {
    private transient final NumberPath path;

    NumberConsider(final Object path) {
        this.path = (NumberPath) path;
    }

    @Override
    @SuppressWarnings("all")
    public BooleanExpression operator(final String op, final Object value) {
        BooleanExpression predicate = null;
        final Integer integer = Integer.parseInt(value.toString());
        switch (op) {
            case Inquiry.Op.NEQ:
                predicate = path.ne(integer);
                break;
            case Inquiry.Op.EQ:
                predicate = path.eq(integer);
                break;
            case Inquiry.Op.LT:
                predicate = path.lt(integer);
                break;
            case Inquiry.Op.GT:
                predicate = path.gt(integer);
                break;
            default:
                predicate = path.eq(integer);
                break;
        }
        return predicate;
    }
}
