package ru.otus.erinary.algo.simplesorts;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleSort {

    private final Logger logger = LoggerFactory.getLogger(SimpleSort.class);
    private final StopWatch watch = new StopWatch();

    void startWatch() {
        watch.start();
    }

    void stopWatch() {
        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();
    }

    /**
     * Меняет местами элементы в переданном массиве.
     *
     * @param array массив чисел
     * @param a     индекс первого элемента
     * @param b     индекс второго элемента
     */
    void swap(final int[] array, final int a, final int b) {
        int x = array[a];
        array[a] = array[b];
        array[b] = x;
    }
}
