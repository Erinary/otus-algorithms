package ru.otus.erinary.algo.algebra;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ДЗ-03 - Возведение в степень.
 */
public class Exponentiation {

    private static final Logger logger = LoggerFactory.getLogger(Exponentiation.class);
    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        var base = 2;
        var exp = 33;

        watch.start();
        var iterativeResult = iterativePow(base, exp);
        watch.stop();
        logger.info("IterativePow Result: {}", iterativeResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();

        watch.start();
        var multipliedResult = multipliedPow(base, exp);
        watch.stop();
        logger.info("MultipliedPow Result: {}", multipliedResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();

        watch.start();
        var decompositionResult = expDecompositionPow(base, exp);
        watch.stop();
        logger.info("DecompositionPow Result: {}", decompositionResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());
    }

    /**
     * Итеративный алгоритм O(N).
     *
     * @param base основание степени
     * @param exp  показатель степени
     * @return результат
     */
    private static double iterativePow(final double base, final long exp) {
        double result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Алгоритм через домножение O(N/2 + log(N)).
     *
     * @param base основание степени
     * @param exp  показатель степени
     * @return результат
     */
    private static double multipliedPow(final double base, final long exp) {
        double result = base;
        var twoFactorExp = (long) Math.floor(Math.log(exp) / Math.log(2));
        var restExp = exp - Math.round(iterativePow(2, twoFactorExp));

        for (int i = 0; i < twoFactorExp; i++) {
            result = result * result;
        }

        return result * iterativePow(base, restExp);
    }

    /**
     * Алгоритм через двоичное разложение показателя степени O(log(N)).
     *
     * @param base основание степени
     * @param exp  показатель степени
     * @return результат
     */
    private static double expDecompositionPow(final double base, final long exp) {
        var n = exp;
        double d = base;
        double result = 1;

        if (n % 2 == 1) {
            result *= d;
        }
        while (n > 1) {
            n /= 2;
            d *= d;
            if (n % 2 == 1) {
                result *= d;
            }
        }

        return result;
    }
}
