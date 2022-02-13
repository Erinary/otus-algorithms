package ru.otus.erinary;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.erinary.algo.luckyticket.LuckyTicket;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        var watch = new StopWatch();
        watch.start();

        var count = LuckyTicket.getLuckyTicketsRecursively(5, 0, 0);

        watch.stop();
        logger.info("{}", count);
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
    }
}
