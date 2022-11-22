package ru.otus.erinary.algo.sorting.heapsort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * ДЗ-07 - Пирамидальная сортировка. SelectionSort, HeapSort.
 */
public class HeapSortApp {

    private static final Logger logger = LoggerFactory.getLogger(HeapSortApp.class);

    public static void main(String[] args) {
        compareSorts();
    }

    public static void compareSorts() {
        logger.info("SelectionSort");
        int[] array;
        var selectionSort = new SelectionSort();
        for (int i = 100; i <= 100000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            selectionSort.sort(array);
        }

        logger.info("HeapSort");
        var heapSort = new HeapSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            heapSort.sort(array);
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
