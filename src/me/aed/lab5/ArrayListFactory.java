package me.aed.lab5;

import java.util.ArrayList;

public class ArrayListFactory<TType> {
    protected final ArrayList<TType> list = new ArrayList<>();

    public final void add(final TType item) {
        list.add(item);
    }

    public final ArrayList<TType> get() {
        return list;
    }

    public final void clean() {
        list.clear();
    }

    public final void removeLast() {
        list.remove(list.size() - 1);
    }
}
