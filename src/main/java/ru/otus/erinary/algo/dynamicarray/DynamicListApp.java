package ru.otus.erinary.algo.dynamicarray;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ДЗ-04 - Динамические массивы.
 */
public class DynamicListApp {

    private static final Logger logger = LoggerFactory.getLogger(DynamicListApp.class);

    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        logger.info("SingleArray");
        var singleList = new SingleArray<Integer>();
        for (int n = 100; n <= 100000; n *= 10) {
            fillArrayList(singleList, n);
        }

        logger.info("VectorArray");
        var vectorList = new VectorArray<Integer>(20);
        for (int n = 100; n <= 1000000; n *= 10) {
            fillArrayList(vectorList, n);
        }
    }

    private static void fillArrayList(final DynamicList<Integer> list, final int total) {
        watch.start();

        for (int i = 0; i < total; i++) {
            list.put(i);
        }

        watch.stop();
        logger.info("Total: {}", total);
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();
    }
}
