package ru.otus.erinary.algo.sorting.quicksort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.erinary.algo.sorting.simplesorts.SimpleSortApp;

import java.util.Random;

/**
 * ДЗ-08 - Быстрая и внешняя сортировки. QuickSort, MergeSort, ExternalSort.
 */
public class QuickSortApp {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSortApp.class);

    public static void main(String[] args) {
        logger.info("QuickSort");
        int[] array;
        var quickSort = new QuickSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            quickSort.sort(array);
        }

        logger.info("MergeSort");
        var mergeSort = new MergeSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            mergeSort.sort(array);
        }
    }

    private static int[] createRandomArray(final int length) {
        var random = new Random();
        var array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }
}
