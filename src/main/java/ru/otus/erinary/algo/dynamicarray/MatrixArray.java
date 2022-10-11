package ru.otus.erinary.algo.dynamicarray;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Список на массиве массивов.
 */
public class MatrixArray<T> implements DynamicList<T>, Iterable<T> {

    private DynamicList<DynamicList<T>> matrix;
    private final int capacity;
    private int size;

    public MatrixArray(final int capacity) {
        this.capacity = capacity;
        this.matrix = new SingleArray<>();
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
        var innerArray = matrix.get(index / capacity);
        return innerArray.get(index % capacity);
    }

    @Override
    public void put(final T item) {
        if (size == matrix.size() * capacity) {
            matrix.put(new VectorArray<>(capacity));
        }
        matrix.get(size / capacity).put(item);
        size++;
    }

    @Override
    public void put(final T item, final int index) {
        if (size == matrix.size() * capacity) {
            matrix.put(new VectorArray<>(capacity));
        }

        var copy = new SingleArray<DynamicList<T>>();
        int count = 0;
        for (T elem : this) {
            if (count == copy.size() * capacity) {
                copy.put(new VectorArray<>(capacity));
            }
            if (count == index) {
                copy.get(count / capacity).put(item);
                count++;
                if (count == copy.size() * capacity) {
                    copy.put(new VectorArray<>(capacity));
                }
            }
            copy.get(count / capacity).put(elem);
            count++;
        }

        matrix = copy;
        size++;
    }

    @Override
    public T remove(final int index) {
        var item = get(index);

        var copy = new SingleArray<DynamicList<T>>();
        int count = 0;
        for (int i = 0; i < index; i++) {
            if (count == copy.size() * capacity) {
                copy.put(new VectorArray<>(capacity));
            }
            copy.get(count / capacity).put(get(i));
            count++;
        }
        for (int i = index + 1; i < size; i++) {
            if (count == copy.size() * capacity) {
                copy.put(new VectorArray<>(capacity));
            }
            copy.get(count / capacity).put(get(i));
            count++;
        }
        matrix = copy;
        size--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new MatrixItr<>(this);
    }

    @Override
    public String toString() {
        var outerIt = matrix.iterator();
        if (!outerIt.hasNext()) {
            return "{}";
        }
        var sb = new StringBuilder();
        sb.append('{');

        while (true) {
            var item = outerIt.next();
            sb.append(item.toString());
            if (!outerIt.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    private static class MatrixItr<E> implements Iterator<E> {

        private int cursor;

        private final MatrixArray<E> matrix;

        MatrixItr(final MatrixArray<E> matrix) {
            this.matrix = matrix;
        }

        @Override
        public boolean hasNext() {
            return cursor < matrix.size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= matrix.size) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return matrix.get(i);
        }
    }
}
