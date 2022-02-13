package ru.otus.erinary;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        var watch = new StopWatch();
        watch.start();

        logger.info("Hello World");

        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
    }
}
