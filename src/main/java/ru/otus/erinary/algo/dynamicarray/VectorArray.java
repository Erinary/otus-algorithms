package ru.otus.erinary.algo.dynamicarray;

/**
 * Список на массиве заданного размера.
 */
public class VectorArray<T> implements CustomArrayList<T>{

    private final int vector;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public VectorArray(final int vector) {
        this.vector = vector;
        array = (T[]) (new Object[vector]);
        size = 0;
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
    public void put(T item, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
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
