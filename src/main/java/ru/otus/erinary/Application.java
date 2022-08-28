package ru.otus.erinary;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.erinary.algo.dynamicarray.CustomArrayList;
import ru.otus.erinary.algo.dynamicarray.FactorArray;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        testCustomArrayList();
    }

    /**
     * ДЗ-04 - Динамические массивы
     */
    private static void testCustomArrayList() {
        CustomArrayList<Integer> list = new FactorArray<>();
        fillArrayList(list, 1000);
        fillArrayList(list, 10000);
        fillArrayList(list, 100000);
        fillArrayList(list, 1000000);
        fillArrayList(list, 10000000);
    }

    private static void fillArrayList(final CustomArrayList<Integer> list, final int total) {
        var watch = new StopWatch();
        watch.start();

        for (int i = 0; i < total; i++) {
            list.put(i);
        }

        watch.stop();
        logger.info("Total: {}", total);
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
    }
}
