package ru.otus.erinary.algo.dynamicarray;

import java.util.Arrays;

/**
 * Список на массиве без заданного размера.
 */
public class SingleArray<T> implements CustomArrayList<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public SingleArray() {
        array = (T[]) (new Object[0]);
    }

    @Override
    public int size() {
        return array.length;
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
        resize();
        array[size() - 1] = item;
    }

    @Override
    public void put(final T item, final int index) {
        resize();
        System.arraycopy(array, index, array, index + 1, size() - index - 1);
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(final int index) {
        T item = array[index];
        T[] newArray = (T[]) (new Object[size() - 1]);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size() - 1 - index);
        array = newArray;
        return item;
    }

    @Override
    public String toString() {
        var it = Arrays.stream(array).iterator();
        if (!it.hasNext())
            return "[]";

        var sb = new StringBuilder();
        sb.append('[');

        while (true) {
            T item = it.next();
            sb.append(item);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) (new Object[size() + 1]);
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }
        array = newArray;
    }
}
