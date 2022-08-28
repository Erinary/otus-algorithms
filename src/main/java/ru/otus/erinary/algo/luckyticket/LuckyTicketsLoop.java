package ru.otus.erinary.algo.luckyticket;

/**
 * 6-значный билет считается счастливым,
 * если сумма 3 первых цифр равна сумме последних 3 цифр.
 * Посчитать, сколько существует счастливых 6-значных билетов.
 * <p>
 * Сложность - O(10^N), где N = 6 (кол-во цифр в билете)
 */
public class LuckyTicketsLoop extends LuckyTickets {

    @Override
    public long getLuckyTicketsCount(final int n) {
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
}
