package ru.otus.erinary.algo.luckyticket;

public class LuckyTicket {

    /**
     * 6-значный билет считается счастливым,
     * если сумма 3 первых цифр равна сумме последних 3 цифр.
     * Посчитать, сколько существует счастливых 6-значных билетов.
     * <p>
     * Сложность - O(10^6)
     */
    public static long getLuckyTickets() {
        long count = 0;

        for (int a1 = 0; a1 < 10; a1++) {
            for (int a2 = 0; a2 < 10; a2++) {
                for (int a3 = 0; a3 < 10; a3++) {
                    int sumA = a1 + a2 + a3;

                    for (int b1 = 0; b1 < 10; b1++) {
                        for (int b2 = 0; b2 < 10; b2++) {
                            for (int b3 = 0; b3 < 10; b3++) {
                                int sumB = b1 + b2 + b3;
                                if (sumA == sumB) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    private static int count = 0;

    /**
     * Сложность - O(100^N)
     */
    public static long getLuckyTicketsRecursively(final int n, int sumA, int sumB) {
        if (n == 0) {
            if (sumA == sumB) {
                count++;
            }
            return count;
        }
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                getLuckyTicketsRecursively(n - 1, sumA + a, sumB + b);
            }
        }
        return count;
    }
}
