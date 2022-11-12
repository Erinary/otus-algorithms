package ru.otus.erinary.algo.simplesorts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * ДЗ-06 - Простые сортировки. BubbleSort, InsertionSort, ShellSort.
 */
public class SimpleSortApp {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSortApp.class);

    public static void main(String[] args) {
        compareSorts();
        checkOptimizedBubble();
    }

    public static void compareSorts() {
        logger.info("BubbleSort");
        int[] array;
        var bubbleSort = new BubbleSort();
        for (int i = 100; i <= 100000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            bubbleSort.sort(array);
        }

        logger.info("BubbleSort - optimized");
        for (int i = 100; i <= 100000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            bubbleSort.optimizedSort(array);
        }

        logger.info("InsertionSort");
        var insertionSort = new InsertionSort();
        for (int i = 100; i <= 100000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            insertionSort.sort(array);
        }

        logger.info("InsertionSort - shifted");
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            insertionSort.sortShifted(array);
        }

        logger.info("InsertionSort - binary shifted");
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            insertionSort.sortBinaryShifted(array);
        }

        logger.info("ShellSort");
        var shellSort = new ShellSort();
        for (int i = 100; i <= 1000000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createRandomArray(i);
            shellSort.sort(array);
        }
    }

    public static void checkOptimizedBubble() {
        logger.info("BubbleSort comparison on sorted array");
        int[] array;
        var sort = new BubbleSort();
        for (int i = 100; i <= 100000; i *= 10) {
            logger.info("Elements: {}", i);
            array = createSortedArray(i);

            logger.info("Optimized BubbleSort");
            sort.optimizedSort(array);
            logger.info("Simple BubbleSort");
            sort.sort(array);
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

    private static int[] createSortedArray(final int length) {
        var array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }
}
