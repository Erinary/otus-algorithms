package ru.otus.erinary.algo.luckyticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ДЗ-02 - Счастливый билеты.
 */
public class LuckyTicketsApp {

    private static final Logger logger = LoggerFactory.getLogger(LuckyTicketsApp.class);

    public static void main(String[] args) {

        LuckyTickets nestedLoopTickets = new LuckyTicketsLoop();
        logger.info("Nested loop:");
        //для этой реализации n всегда равно 3, укажем его как заглушку
        nestedLoopTickets.getLuckyTickets(3);

        LuckyTickets recursionTickets = new LuckyTicketsRecursion();
        logger.info("Recursion loop:");
        recursionTickets.getLuckyTickets(1);
        recursionTickets.getLuckyTickets(2);
        recursionTickets.getLuckyTickets(3);
        recursionTickets.getLuckyTickets(4);
    }
}
