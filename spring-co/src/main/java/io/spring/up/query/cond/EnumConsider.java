package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import io.vertx.core.json.JsonArray;
import io.vertx.up.atom.query.Inquiry;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class EnumConsider implements Consider {
    private transient final EnumPath path;

    EnumConsider(final Object path) {
        this.path = (EnumPath) path;
    }

    @Override
    public BooleanExpression operator(final String op, final Object value) {
        BooleanExpression predicate = null;
        switch (op) {
            case Inquiry.Op.EQ:
                predicate = getEnumEq(value);
                break;
            case Inquiry.Op.IN: {
                if (null != value) {
                    predicate = path.in(fromList(value));
                }
            }
            break;
            case Inquiry.Op.NOT_IN: {
                if (null != value) {
                    predicate = path.notIn(fromList(value));
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
                predicate = getEnumEq(value);
                break;
        }
        return predicate;
    }

    private BooleanExpression getEnumEq(final Object value) {
        return null == value ? path.isNull() :
                path.eq(fromString(value.toString()));
    }

    private List<String> fromList(final Object value) {
        List<String> arrays = new ArrayList<>();
        if (value instanceof JsonArray) {
            arrays = ((JsonArray) value).getList();
        }
//        final List<Enum> enums = new ArrayList<>();
//        arrays.forEach(item -> enums.add(fromString(item)));
        return arrays;
    }

    private Enum fromString(final String value) {
        return Enum.valueOf(((Class<Enum>) this.path.getType()), value.toString());
    }
}
