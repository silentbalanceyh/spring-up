package io.spring.up.aiki;

import java.util.HashSet;

class Reduce {
    static <T> HashSet<T> rdcHashSet(final HashSet<T> collection, final T element) {
        collection.add(element);
        return collection;
    }
}
