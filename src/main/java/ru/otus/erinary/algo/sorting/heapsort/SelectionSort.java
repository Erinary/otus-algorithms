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

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    public void sort(final int[] array) {
        watcher.startWatch();
        int max = findMax(array, array.length);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, max, i);
            max = findMax(array, i);
        }
        watcher.stopWatch();
    }

    /**
     * Метод поиска индекса максимального элемента в массиве.
     *
     * @param array    массив чисел
     * @param position позиция в массиве, до которой нужно искать максимальный элемент
     * @return индекс максимального элемента
     */
    private int findMax(final int[] array, final int position) {
        int x = 0;
        for (int i = 1; i < position; i++) {
            if (array[i] > array[x]) {
                x = i;
            }
        }
        return x;
    }
}
