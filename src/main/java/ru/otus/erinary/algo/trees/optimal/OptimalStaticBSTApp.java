package ru.otus.erinary.algo.trees.optimal;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.erinary.algo.trees.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * ДЗ-11 - Оптимальное дерево поиска.
 */
public class OptimalStaticBSTApp {

    private static final Random RANDOM = new Random();
    private static final Logger logger = LoggerFactory.getLogger(OptimalStaticBSTApp.class);
    private static final StopWatch watch = new StopWatch();

    public static void main(String[] args) {
        int keyBound = 4000000;
        int weightBound = 250;
        var pairs = createWeightedPairs(keyBound, weightBound);

        logger.info("OptimalBST based on weight sorting strategy: creation");
        watch.start();
        var weightSortedTree = new OptimalStaticBST(pairs, OptimalStaticBST.BuildingStrategy.WEIGHT_SORTING);
        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();

        acceptAction("OptimalBST based on weight sorting strategy: search random element",
                tree -> {
                    for (int i = 0; i < keyBound / 10; i++) {
                        int pairToSearchIdx = RANDOM.nextInt(pairs.size());
                        var nodeToSearch = pairs.get(pairToSearchIdx);
                        for (int j = 0; j < nodeToSearch.getRight(); j++) {
                            tree.search(nodeToSearch.getLeft());
                        }
                    }
                },
                weightSortedTree
        );

        logger.info("OptimalBST based on key sorting strategy: creation");
        watch.start();
        var keySortedTree = new OptimalStaticBST(pairs, OptimalStaticBST.BuildingStrategy.KEY_SORTING);
        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime() + " ms");
        watch.reset();

        acceptAction("OptimalBST based on key sorting strategy: search random element",
                tree -> {
                    for (int i = 0; i < keyBound / 10; i++) {
                        int pairToSearchIdx = RANDOM.nextInt(pairs.size());
                        var nodeToSearch = pairs.get(pairToSearchIdx);
                        for (int j = 0; j < nodeToSearch.getRight(); j++) {
                            tree.search(nodeToSearch.getLeft());
                        }
                    }
                },
                keySortedTree
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

    private static List<Pair<Integer, Integer>> createWeightedPairs(final int keyBound, final int weightBound) {
        var list = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < keyBound; i++) {
            list.add(Pair.of(RANDOM.nextInt(keyBound), RANDOM.nextInt(weightBound) + 1));
        }
        return list;
    }
}
