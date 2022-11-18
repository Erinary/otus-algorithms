package ru.otus.erinary.algo.sorting.quicksort;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Сортировка слиянием.
 * <p>
 * Рекурсивный алгоритм, использующий идею разделения массива на части, сортировки элементов в этих частях, и
 * последующего их объединения.
 * Алгоритм делит массив напополам, рекурсивно вызвывается для полученных частей, сортирует элементы в них, и в конце
 * объединяет части массива.
 * Сложность - O(N*log(N).
 */
public class MergeSort extends AbstractSort {

    @Override
    public void sort(final int[] array) {
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
        if (left >= right) {
            return;
        }
        int pivot = (left + right) / 2;
        internalSort(array, left, pivot);
        internalSort(array, pivot + 1, right);
        merge(array, left, pivot, right);
    }

    /**
     * Метод слияния отсортированных частей массива.
     *
     * @param array массив чисел
     * @param left  левая граница
     * @param pivot опорный элемент
     * @param right правая граница
     */
    private void merge(final int[] array, final int left, final int pivot, final int right) {
        int[] memory = new int[right - left + 1];
        int i = left;       //индекс начала левой части массива
        int j = pivot + 1;  //индекс начала правой части массива
        int m = 0;
        while (i <= pivot && j <= right) {
            if (array[i] < array[j]) {
                memory[m++] = array[i++];
            } else {
                memory[m++] = array[j++];
            }
        }
        while (i <= pivot) {
            memory[m++] = array[i++];
        }
        while (j <= right) {
            memory[m++] = array[j++];
        }
        System.arraycopy(memory, 0, array, left, memory.length);
    }

}
