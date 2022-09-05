package ru.otus.erinary.algo.algebra;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * ДЗ-03 - Поиск чисел Фибоначчи.
 */
public class Fibonacci {

    private static final Logger logger = LoggerFactory.getLogger(Fibonacci.class);
    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        long number = 30;

        watch.start();
        var recursiveResult = recursiveSearch(number);
        watch.stop();
        logger.info("Recursion method for '{}': {}", number, recursiveResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();

        watch.start();
        var iterativeResult = iterativeSearch(number);
        watch.stop();
        logger.info("Iterative method for '{}': {}", number, iterativeResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();

        watch.start();
        var goldenRationResult = goldenRationSearch(number);
        watch.stop();
        logger.info("Golden ration method for '{}': {}", number, goldenRationResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();

        watch.start();
        var matrixResult = matrixSearch(number);
        watch.stop();
        logger.info("Matrix method for '{}': {}", number, matrixResult);
        logger.info("Time Elapsed: {} ms", watch.getTime());

    }

    /**
     * Рекурсивный метод O(2^N).
     *
     * @param number порядковый номер искомого числа в ряду Фибоначчи.
     * @return значение числа Фибоначчи
     */
    private static long recursiveSearch(final long number) {
        if (number <= 1) {
            return number;
        }
        return recursiveSearch(number - 1) + recursiveSearch(number - 2);
    }

    /**
     * Итеративный метод O(N).
     *
     * @param number порядковый номер искомого числа в ряду Фибоначчи.
     * @return значение числа Фибоначчи
     */
    private static long iterativeSearch(final long number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        long result = 0;
        long a = 1;
        long b = 1;
        for (int i = 2; i < number; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    /**
     * Метод с формулой золотого сечения. Сложность зависит от сложности алгоритма возведения в степень, но на больших
     * значениях из-за округления итоговый результат будет неточным - ошибки начинаются с ~70 числа.
     *
     * @param number порядковый номер искомого числа в ряду Фибоначчи.
     * @return значение числа Фибоначчи
     */
    private static long goldenRationSearch(final long number) {
        double phi = (Math.sqrt(5) + 1) / 2; // уже на этом шаге сталкиваемся с зарезервированной точностью для хранения вещественных чисел
        return Math.round(Math.pow(phi, number) / Math.sqrt(5));
    }

    /**
     * Метод с возведением матрицы в степень через двоичное разложение показателя степени O(log(N)).
     *
     * @param number порядковый номер искомого числа в ряду Фибоначчи.
     * @return значение числа Фибоначчи
     */
    private static BigInteger matrixSearch(final long number) {
        if (number == 1 || number == 2) {
            return BigInteger.ONE;
        }

        var exp = number - 1;
        var result = Matrix2D.IDENTITY;
        var base = Matrix2D.BASE;

        if (exp % 2 == 1) {
            result = result.multiply(base);
        }
        while (exp > 1) {
            base = base.multiply(base);
            exp /= 2;
            if (exp % 2 == 1) {
                result = result.multiply(base);
            }
        }
        return result.getA11();
    }

    /**
     * Простой класс, представляющий собой матрицу 2х2.
     */
    private static class Matrix2D {
        private static final Matrix2D IDENTITY = new Matrix2D(1, 0, 0, 1);
        private static final Matrix2D BASE = new Matrix2D(1, 1, 1, 0);

        private final BigInteger a11;
        private final BigInteger a12;
        private final BigInteger a21;
        private final BigInteger a22;

        Matrix2D(final BigInteger a11, final BigInteger a12, final BigInteger a21, final BigInteger a22) {
            this.a11 = a11;
            this.a12 = a12;
            this.a21 = a21;
            this.a22 = a22;
        }

        Matrix2D(final long a11, final long a12, final long a21, final long a22) {
            this.a11 = BigInteger.valueOf(a11);
            this.a12 = BigInteger.valueOf(a12);
            this.a21 = BigInteger.valueOf(a21);
            this.a22 = BigInteger.valueOf(a22);
        }

        Matrix2D multiply(final Matrix2D matrix) {
            return new Matrix2D(
                    a11.multiply(matrix.getA11()).add(a12.multiply(matrix.getA21())),
                    a11.multiply(matrix.getA12()).add(a12.multiply(matrix.getA22())),
                    a21.multiply(matrix.getA11()).add(a22.multiply(matrix.getA21())),
                    a21.multiply(matrix.getA12()).add(a22.multiply(matrix.getA22()))

            );
        }

        BigInteger getA11() {
            return a11;
        }

        BigInteger getA12() {
            return a12;
        }

        BigInteger getA21() {
            return a21;
        }

        BigInteger getA22() {
            return a22;
        }
    }
}
