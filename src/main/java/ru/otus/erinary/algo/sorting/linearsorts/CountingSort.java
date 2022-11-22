package ru.otus.erinary.algo.sorting.linearsorts;

import ru.otus.erinary.algo.sorting.AbstractSort;

import java.util.Arrays;

/**
 * Сортировка подсчетом.
 * <p>
 * В алгоритме сначала подсчитывается количество вхождений каждого числа, значения сохраняются во вспомогательный массив.
 * При проходе по вспомогательному массиву в изначальный массив записывается число подсчитанное количество раз.
 * Сложность - O(N).
 */
public class CountingSort extends AbstractSort {

    @Override
    public void sort(final int[] array) {
        watcher.startWatch();

        int max = array[findIndexMax(array, array.length)];
        int[] counter = new int[max + 1];

        for (int i : array) {
            counter[i]++;
        }
        for (int j = 1; j <= max; j++) {
            counter[j] = counter[j] + counter[j - 1];
        }
        var copy = Arrays.copyOf(array, array.length);
        for (int i = array.length - 1; i >= 0; i--) {
            var elem = copy[i];
            counter[elem] = counter[elem] - 1;
            array[counter[elem]] = elem;
        }

        watcher.stopWatch();
    }
}
