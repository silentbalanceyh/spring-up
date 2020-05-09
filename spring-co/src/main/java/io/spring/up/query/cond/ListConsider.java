package io.spring.up.query.cond;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ListPath;
import io.vertx.core.json.JsonArray;
import io.vertx.up.atom.query.Inquiry;

import java.util.ArrayList;
import java.util.List;

public class ListConsider implements Consider {

    private transient final ListPath path;

    ListConsider(final Object path) {
        this.path = (ListPath) path;
    }

    @Override
    @SuppressWarnings("all")
    public BooleanExpression operator(final String op, final Object value) {
        BooleanExpression predicate = null;
        switch (op) {
            case Inquiry.Op.EQ:
                predicate = path.contains(value);
                break;
            case Inquiry.Op.IN: {
                if (null != value) {
                    List<String> arrays = new ArrayList<>();
                    if (value instanceof JsonArray) {
                        arrays = ((JsonArray) value).getList();
                    }
                    predicate = path.contains(arrays);
                }
            }
            break;
            case Inquiry.Op.NULL: {
                predicate = path.isEmpty();
            }
            break;
            case Inquiry.Op.NOT_NULL: {
                predicate = path.isNotEmpty();
            }
            break;
        }
        return predicate;
    }
}
