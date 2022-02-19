package ru.otus.erinary.algo.dynamicarray;

/**
 * Список на массиве с увеличением на множитель.
 */
public class FactorArray<T> implements CustomArrayList<T> {

    private static final int FACTOR = 2;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public FactorArray() {
        array = (T[]) (new Object[10]);
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

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) (new Object[size() * FACTOR + 1]);
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }
        array = newArray;
    }

}
