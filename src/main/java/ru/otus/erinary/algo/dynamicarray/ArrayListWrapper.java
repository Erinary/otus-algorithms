package ru.otus.erinary.algo.dynamicarray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Обертка над стандартной реализацией {@link ArrayList}.
 */
public class ArrayListWrapper<T> implements DynamicList<T> {

    private final List<T> list;

    public ArrayListWrapper() {
        this.list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void put(T item) {
        list.add(item);
    }

    @Override
    public void put(T item, int index) {
        list.add(index, item);
    }

    @Override
    public T remove(int index) {
        return list.remove(index);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
