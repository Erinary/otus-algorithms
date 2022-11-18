package ru.otus.erinary.algo.sorting.simplesorts;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Сортировка вставкой.
 * <p>
 * Массив делится на отсортированную (слева) и неотсортированную (справа) части. За одну итерацию берется первый элемент из
 * неотсортированной части, находится подходящая для него позиция в отсортированной, и элемент вставляется на эту
 * позицию (остальные элементы отсортированной части смещаются направо).
 * Сложность - O(N^2).
 */
public class InsertionSort extends AbstractSort {

    @Override
    public void sort(final int[] array) {
        watcher.startWatch();
        for (int i = 1; i < array.length; i++) {
            for (int j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
                swap(array, j, j + 1);
            }
        }
        watcher.stopWatch();
    }

    /**
     * Оптимизированный метод - на каждой итерации в отсортированной части находим место для вставки нового элемента,
     * а отсортированную часть справа сдвигаем.
     *
     * @param array массив чисел
     */
    public void sortShifted(final int[] array) {
        watcher.startWatch();
        int j;
        for (int i = 1; i < array.length; i++) {
            int k = array[i];
            for (j = i - 1; j >= 0 && array[j] > k; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = k;
        }
        watcher.stopWatch();
    }

    /**
     * Оптимизированный метод - вместо сравнения соседних элементов находим нужную позицию для вставки элемента
     * с помощью бинарного поиска.
     *
     * @param array массив чисел
     */
    public void sortBinaryShifted(final int[] array) {
        watcher.startWatch();
        int j;
        for (int i = 1; i < array.length; i++) {
            int k = array[i];
            int index = binarySearch(array, k, 0, i - 1);
            for (j = i - 1; j >= index; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = k;
        }
        watcher.stopWatch();
    }

    private int binarySearch(final int[] array, final int key, final int start, final int end) {
        if (end <= start) {
            if (key > array[start]) {
                return start + 1;
            } else {
                return start;
            }
        }
        int middle = (start + end) / 2;
        if (key == array[middle]) {     //non stable
            return middle + 1;
        }
        if (key > array[middle]) {
            return binarySearch(array, key, middle + 1, end);
        } else {
            return binarySearch(array, key, start, middle - 1);
        }
    }

}
