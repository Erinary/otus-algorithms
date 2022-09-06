package ru.otus.erinary.algo.algebra;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ДЗ-03 - Поиск количества простых чисел.
 */
public class PrimeNumber {

    private static final Logger logger = LoggerFactory.getLogger(PrimeNumber.class);
    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        for (int n = 10; n <= 10000000; n *= 10) {
            //скипнем совсем большие числа, чтобы не ждать
            if (n <= 10000) {
                watch.start();
                var iterativeResult = iterativeCount(n);
                watch.stop();
                logger.info("Iterative method for '{}': {}, Time: {}", n, iterativeResult, watch.getTime());

                watch.reset();
            }

            watch.start();
            var iterativeResultOpt = iterativeCountOptimized(n);
            watch.stop();
            logger.info("Optimized iterative method for '{}': {}, Time: {}", n, iterativeResultOpt, watch.getTime());

            watch.reset();

            watch.start();
            var iterativeCachedResult = cachedCount(n);
            watch.stop();
            logger.info("Cached iterative method for '{}': {}, Time: {}", n, iterativeCachedResult, watch.getTime());

            watch.reset();

            logger.info("-------------");
        }
    }

    /**
     * Поиск через перебор делителей O(N^2).
     *
     * @param number диапазон, в котором ищем простые числа
     * @return количество простых чисел
     */
    private static long iterativeCount(final int number) {
        long count = 0;
        for (int i = 2; i <= number; i++) {
            long divisorCount = 0;
            for (int d = 1; d <= i; d++) {
                if (i % d == 0) {
                    divisorCount++;
                }
            }
            if (divisorCount == 2) {
                count++;
            }
        }
        return count;
    }

    /**
     * Оптимизированный поиск через перебор делителей O(N*sqrt(N)).
     *
     * @param number диапазон, в котором ищем простые числа
     * @return количество простых чисел
     */
    private static long iterativeCountOptimized(final int number) {
        long count = 0;
        for (int i = 2; i <= number; i++) {
            if (isPrimeOptimized(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrimeOptimized(final int number) {
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        var sqrt = (long) Math.sqrt(number);
        for (int d = 3; d <= sqrt; d += 2) {
            if (number % d == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Перебор делителей с кэшом найденных простых чисел O(N*ln(N)).
     *
     * @param number диапазон, в котором ищем простые числа
     * @return количество простых чисел
     */
    private static long cachedCount(final int number) {
        int count = 0;
        long[] cache = new long[number / 2];
        cache[count++] = 2;
        for (int i = 3; i <= number; i++) {
            if (isPrimeCached(i, cache)) {
                cache[count++] = i;
            }
        }
        return count;
    }

    private static boolean isPrimeCached(final int number, final long[] cache) {
        var sqrt = (long) Math.sqrt(number);
        for (int i = 0; cache[i] <= sqrt; i++) {
            if (number % cache[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
