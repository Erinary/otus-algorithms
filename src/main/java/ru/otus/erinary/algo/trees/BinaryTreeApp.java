package ru.otus.erinary.algo.trees;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.function.Consumer;

/**
 * ДЗ-10 - Двоичные деревья поиска.
 */
public class BinaryTreeApp {

    private static final Random RANDOM = new Random();
    private static final Logger logger = LoggerFactory.getLogger(BinaryTreeApp.class);
    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        var size = 25000;

        logger.info("BST based on random array");
        logger.info("RandomElementTree: creation");
        var randomArray = createRandomArray(size);
        watch.start();
        var randomTree = new BinarySearchTree(randomArray);

        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();

        acceptAction("RandomElementTree: search random element",
                tree -> {
                    for (int i = 0; i < size / 10; i++) {
                        tree.search(RANDOM.nextInt(size));
                    }
                },
                randomTree
        );

        acceptAction("RandomElementTree: delete random element",
                tree -> {
                    for (int i = 0; i < size / 10; i++) {
                        tree.search(RANDOM.nextInt(size));
                    }
                },
                randomTree
        );

        logger.info("BST based on ascending array");
        logger.info("SortedElementTree: creation");
        int[] ascArray = createAscArray(size);
        watch.start();
        var sortedTree = new BinarySearchTree(ascArray);

        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();

        acceptAction("SortedElementTree: search random element",
                tree -> {
                    for (int i = 0; i < size / 10; i++) {
                        tree.search(RANDOM.nextInt(size));
                    }
                },
                sortedTree
        );

        acceptAction("SortedElementTree: delete random element",
                tree -> {
                    for (int i = 0; i < size / 10; i++) {
                        tree.delete(RANDOM.nextInt(size));
                    }
                },
                sortedTree
        );

    }

    private static void acceptAction(final String actionName, final Consumer<BinarySearchTree> action, final BinarySearchTree tree) {
        logger.info(actionName);
        watch.start();
        action.accept(tree);
        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();
    }

    private static int[] createRandomArray(final int length) {
        var array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = RANDOM.nextInt(length);
        }
        return array;
    }

    private static int[] createAscArray(final int length) {
        var array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }
}
