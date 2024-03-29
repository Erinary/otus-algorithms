package ru.otus.erinary.algo.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Список на массиве заданного размера.
 */
public class VectorArray<T> extends CustomArrayList<T> {

    private final int vector;
    private int size;

    @SuppressWarnings("unchecked")
    public VectorArray(final int vector) {
        super((T[]) (new Object[vector]));
        this.vector = vector;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T get(final int index) {
        return array[index];
    }

    @Override
    public void put(final T item) {
        if (size == array.length) {
            resize();
        }
        array[size] = item;
        size++;
    }

    @Override
    public void put(final T item, final int index) {
        if (size == array.length) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = item;
        size++;
    }

    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    @Override
    public T remove(final int index) {
        T item = array[index];
        T[] newArray = (T[]) (new Object[size() - 1]);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size() - 1 - index);
        array = newArray;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array).iterator();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) (new Object[size() + vector]);
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }
        array = newArray;
    }
}
