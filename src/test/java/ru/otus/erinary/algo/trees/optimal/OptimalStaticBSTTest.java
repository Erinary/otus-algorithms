package ru.otus.erinary.algo.trees.optimal;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import ru.otus.erinary.algo.trees.BinaryTreePrinter;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptimalStaticBSTTest {

    private static final BinaryTreePrinter printer = new BinaryTreePrinter();

    @Test
    void createWithWeightSortingStrategy() {
        var tree = new OptimalStaticBST(createPairs(), OptimalStaticBST.BuildingStrategy.WEIGHT_SORTING);

        assertEquals(10, tree.getRoot().getKey());
        assertEquals(3.1d, tree.getHeight(), 0.000001d);

        System.out.println(printer.buildTreeDiagram(tree));
    }

    @Test
    void createWithKeySortingStrategy() {
        var tree = new OptimalStaticBST(createPairs(), OptimalStaticBST.BuildingStrategy.KEY_SORTING);

        assertEquals(6, tree.getRoot().getKey());
        assertEquals(2.85d, tree.getHeight(), 0.000001d);

        System.out.println(printer.buildTreeDiagram(tree));
    }

    private List<Pair<Integer, Integer>> createPairs() {
        return Arrays.asList(
                Pair.of(1, 5),
                Pair.of(2, 20),
                Pair.of(3, 5),
                Pair.of(4, 10),
                Pair.of(5, 5),
                Pair.of(6, 10),
                Pair.of(7, 10),
                Pair.of(8, 5),
                Pair.of(9, 5),
                Pair.of(10, 25)
        );
    }

}