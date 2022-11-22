package ru.otus.erinary.algo.sorting;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSort {

    protected SortWatcher watcher = new SortWatcher();

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    public abstract void sort(final int[] array);

    /**
     * Меняет местами элементы в переданном массиве.
     *
     * @param array массив чисел
     * @param a     индекс первого элемента
     * @param b     индекс второго элемента
     */
    protected void swap(final int[] array, final int a, final int b) {
        int x = array[a];
        array[a] = array[b];
        array[b] = x;
    }

    /**
     * Метод поиска индекса максимального элемента в массиве.
     *
     * @param array    массив чисел
     * @param position позиция в массиве, до которой нужно искать максимальный элемент
     * @return индекс максимального элемента
     */
    protected int findIndexMax(final int[] array, final int position) {
        int x = 0;
        for (int i = 1; i < position; i++) {
            if (array[i] > array[x]) {
                x = i;
            }
        }
        return x;
    }

    /**
     * Класс для запуска таймера и вывода лога.
     */
    public static class SortWatcher {
        private final Logger logger = LoggerFactory.getLogger(SortWatcher.class);
        private final StopWatch watch = new StopWatch();

        public void startWatch() {
            watch.start();
        }

        public void stopWatch() {
            watch.stop();
            logger.info("Time Elapsed: " + watch.getTime() + " ms");
            watch.reset();
        }
    }
}
