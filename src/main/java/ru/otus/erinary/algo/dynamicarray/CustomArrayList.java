package ru.otus.erinary.algo.dynamicarray;

/**
 * Интерфейс динамического списка.
 */
public interface CustomArrayList<T> {

    int size();

    boolean isEmpty();

    T get(int index);

    void put(T item);

}
