package me.aed.shared;

import java.util.ArrayList;

public final class CollectorList<T> {
    private final ArrayList<T> list = new ArrayList<>();

    public void add(final T item) {
        list.add(item);
    }

    public void clear() {
        list.clear();
    }

    public T get(final int index) {
        if (index >= list.size()) return null;
        return list.get(index);
    }

    public void remove() {
        remove(list.size() - 1);
    }

    public void remove(final int index) {
        if (index >= list.size()) return;
        list.remove(index);
    }
}
