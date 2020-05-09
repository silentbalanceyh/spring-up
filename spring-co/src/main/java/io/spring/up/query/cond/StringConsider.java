package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import io.spring.up.cv.Strings;
import io.vertx.core.json.JsonArray;
import io.vertx.up.atom.query.Inquiry;

import java.util.ArrayList;
import java.util.List;

public class StringConsider implements Consider {

    private transient final StringPath path;

    StringConsider(final Object path) {
        this.path = (StringPath) path;
    }

    @Override
    @SuppressWarnings("all")
    public BooleanExpression operator(final String op, Object value) {
        BooleanExpression predicate = null;
        final String hitValue = null == value ? Strings.EMPTY : value.toString();
        switch (op) {
            case Inquiry.Op.NEQ:
                predicate = path.ne(hitValue.toString());
                break;
            case Inquiry.Op.EQ:
                predicate = path.eq(hitValue.toString());
                break;
            case Inquiry.Op.START:
                predicate = path.like(hitValue.toString() + "%");
                break;
            case Inquiry.Op.END:
                predicate = path.like("%" + hitValue.toString());
                break;
            case Inquiry.Op.CONTAIN:
                predicate = path.like("%" + hitValue.toString() + "%");
                break;
            case Inquiry.Op.IN: {
                if (null != value) {
                    List<String> arrays = new ArrayList<>();
                    if (value instanceof JsonArray) {
                        arrays = ((JsonArray) value).getList();
                    }
                    predicate = path.in(arrays);
                }
            }
            break;
            case Inquiry.Op.NOT_IN: {
                if (null != value) {
                    List<String> arrays = new ArrayList<>();
                    if (value instanceof JsonArray) {
                        arrays = ((JsonArray) value).getList();
                    }
                    predicate = path.notIn(arrays);
                }
            }
            break;
            case Inquiry.Op.NULL: {
                predicate = path.isNull();
            }
            break;
            case Inquiry.Op.NOT_NULL: {
                predicate = path.isNotNull();
            }
            break;
            default:
                predicate = path.eq(hitValue.toString());
                break;
        }
        return predicate;
    }
}
