package ru.otus.erinary.algo.sorting.simplesorts;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Сортировка Шелла.
 * <p>
 * Сравниваются элементы не только стоящие рядом, но и на определенном расстоянии друг от друга.
 * Сложность - O(~N^1.5) - зависит от выбранного шага (gap).
 */
public class ShellSort extends AbstractSort {

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    @Override
    public void sort(final int[] array) {
        watcher.startWatch();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j >= gap && array[j - gap] > array[j]; j -= gap) {
                    swap(array, j, j - gap);
                }
            }
        }
        watcher.stopWatch();
    }
}
