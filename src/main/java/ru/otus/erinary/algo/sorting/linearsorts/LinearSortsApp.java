package ru.otus.erinary.algo.sorting.linearsorts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * ДЗ-09 - Линейные сортировке. BucketSort, CountingSort, RadixSort.
 */
public class LinearSortsApp {

    private static final Logger logger = LoggerFactory.getLogger(LinearSortsApp.class);

    public static void main(String[] args) {
        logger.info("BucketSort");
        int[] array;
        var bucketSort = new BucketSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            bucketSort.sort(array);
        }

        logger.info("CountingSort");
        var countingSort = new CountingSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            countingSort.sort(array);
        }

        logger.info("RadixSort");
        var radixSort = new RadixSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            radixSort.sort(array);
        }
    }

    private static int[] createRandomArray(final int length) {
        var random = new Random();
        var array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }
}
