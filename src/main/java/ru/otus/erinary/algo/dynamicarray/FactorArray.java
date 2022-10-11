package ru.otus.erinary.algo.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Список на массиве с увеличением на множитель.
 */
public class FactorArray<T> extends CustomArrayList<T> {

    private static final int FACTOR = 2;
    private int size;

    @SuppressWarnings("unchecked")
    public FactorArray() {
        super((T[]) (new Object[0]));
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
        T[] newArray = (T[]) (new Object[size() * FACTOR + 1]);
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }
        array = newArray;
    }

}
