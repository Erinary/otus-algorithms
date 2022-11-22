package ru.otus.erinary.algo.sorting.heapsort;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Сортировка выбором.
 * <p>
 * Массив делится на две части - отсортированную и неотсортированную. За каждую итерацию находит максимальный элемент в
 * неотсортированной части и перемащет его в начало отсортированной.
 * Сложность - O(N^2).
 */
public class SelectionSort extends AbstractSort {

    public void sort(final int[] array) {
        watcher.startWatch();
        int max = findIndexMax(array, array.length);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, max, i);
            max = findIndexMax(array, i);
        }
        watcher.stopWatch();
    }

}
