package ru.otus.erinary.algo.luckyticket;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LuckyTickets {

    private static final Logger logger = LoggerFactory.getLogger(LuckyTickets.class);
    private static final StopWatch watch = new StopWatch();

    public void getLuckyTickets(final int n) {
        watch.start();

        var count = getLuckyTicketsCount(n);
        watch.stop();
        logger.info("Count: {}", count);
        logger.info("Time Elapsed: {} ms", watch.getTime());

        watch.reset();
    }

    protected abstract long getLuckyTicketsCount(final int n);

}
