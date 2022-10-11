package ru.otus.erinary.algo.dynamicarray;

import java.util.Arrays;

/**
 * Класс-родитель для реализаций динамических массивов.
 *
 * @param <T>
 */
public abstract class CustomArrayList<T> implements DynamicList<T> {

    protected T[] array;

    protected CustomArrayList(final T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        var it = Arrays.stream(array).iterator();
        if (!it.hasNext()) {
            return "[]";
        }

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
}
