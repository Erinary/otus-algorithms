package ru.otus.erinary.algo.luckyticket;

/**
 * 2N-значный билет считается счастливым,
 * если сумма N первых цифр равна сумме последних N цифр.
 * Посчитать, сколько существует счастливых 2N-значных билетов.
 * <p>
 * Сложность - O(100^N)
 */
public class LuckyTicketsRecursion extends LuckyTickets {
    @Override
    protected long getLuckyTicketsCount(int n) {
        return getLuckyTickets(new Accumulator(), n, 0, 0);
    }

    private long getLuckyTickets(Accumulator accumulator, final int n, int sumA, int sumB) {
        if (n == 0) {
            if (sumA == sumB) {
                accumulator.count++;
            }
            return accumulator.count;
        }
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                getLuckyTickets(accumulator, n - 1, sumA + a, sumB + b);
            }
        }
        return accumulator.count;
    }

    private static class Accumulator {
        private int count = 0;
    }
}
