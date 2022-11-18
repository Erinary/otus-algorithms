package ru.otus.erinary.algo.sorting.quicksort;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Быстрая сортировка.
 * <p>
 * Рекурсивный алгоритм, использующий идею разделения массива на части, сортировки элементов в этих частях, и
 * последующего их объединения.
 * В алгоритме выбирается некоторый опорный элемент, и массив подготавливается таким образом, что в левой его части
 * оказываются элементы меньше опорного, а в правой - больше опорного. Далее метод рекурсивно вызывается для
 * получившихся частей массива.
 * Сложность - O(N*log(N).
 */
public class QuickSort extends AbstractSort {

    @Override
    public void sort(int[] array) {
        watcher.startWatch();
        internalSort(array, 0, array.length - 1);
        watcher.stopWatch();
    }

    /**
     * Рекурсивно вызываемый метод сортировки.
     *
     * @param array массив чисел
     * @param left  левая граница
     * @param right правая граница
     */
    private void internalSort(final int[] array, final int left, final int right) {
        if (left >= right || left < 0) {
            return;
        }
        int pivot = split(array, left, right);
        internalSort(array, left, pivot - 1);
        internalSort(array, pivot + 1, right);
    }

    /**
     * Метод разделения массива на две условные части, в котором элементы левой меньше выбранного опорного элемента,
     * а элементы правой - больше.
     *
     * @param array массив чисел
     * @param left  левая грация
     * @param right правая граница
     * @return опорный элемент
     * @implNote Имплементация на основе деления массива на три части - меньше опорного элемента, больше опорного элемента
     * и неотсортированные данные
     */
    private int split(final int[] array, final int left, final int right) {
        int p = array[right];
        int m = left - 1;
        for (int j = left; j <= right; j++) {
            if (array[j] <= p) {
                swap(array, ++m, j);
            }
        }
        return m;
    }

    /**
     * Метод разделения массива на две условные части, в котором элементы левой меньше выбранного опорного элемента,
     * а элементы правой - больше.
     *
     * @param array массив чисел
     * @param left  левая грация
     * @param right правая граница
     * @return опорный элемент
     * @implNote Классическая реализация разделения массива на две части.
     */
    @SuppressWarnings("unused")
    private int splitClassic(final int[] array, final int left, final int right) {
        int p = array[right];
        int i = left - 1;
        int j = right + 1;
        while (true) {
            while (array[i] < p) {
                i++;
            }
            while (array[j] > p) {
                j++;
            }
            if (i >= j) {
                return j;
            }
            swap(array, i, j);
        }
    }
}
