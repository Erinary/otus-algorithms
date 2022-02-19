package ru.otus.erinary.algo.dynamicarray;

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

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) (new Object[size() + 1]);
        if (!isEmpty()) {
            System.arraycopy(array, 0, newArray, 0, size());
        }
        array = newArray;
    }
}
