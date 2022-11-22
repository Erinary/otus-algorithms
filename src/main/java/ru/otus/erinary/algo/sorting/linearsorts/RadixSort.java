package ru.otus.erinary.algo.sorting.linearsorts;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Разрядная сортировка.
 * <p>
 * Похоже на сортировку подсчетом, но вместо подсчета вхождений в массив конкретного числа, считаются вхождения по разрядам
 * чисел в массиве (единицам, десяткам, сотням и т. д.) и итеративно делается сортировка по ним.
 * Сложность - O(N).
 */
public class RadixSort extends AbstractSort {

    @Override
    public void sort(final int[] array) {
        watcher.startWatch();

        int max = array[findIndexMax(array, array.length)];
        int digitPlace = 1;
        int[] result = new int[array.length];
        int[] counter;

        while (max / digitPlace > 0) {
            counter = new int[10];
            for (int i : array) {
                counter[(i/digitPlace)%10]++;
            }
            for (int j = 1; j < 10; j++) {
                counter[j] += counter[j - 1];
            }
            for (int i = array.length - 1; i >= 0; i--) {
                result[counter[(array[i]/digitPlace)%10] - 1] = array[i];
                counter[(array[i]/digitPlace)%10]--;
            }
            System.arraycopy(result, 0, array, 0, array.length);
            digitPlace *= 10;
        }

        watcher.stopWatch();
    }
}
