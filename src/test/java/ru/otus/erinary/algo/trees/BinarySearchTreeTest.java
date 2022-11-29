package ru.otus.erinary.algo.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BinarySearchTreeTest {

    private static final BinaryTreePrinter printer = new BinaryTreePrinter();

    @Test
    void insert() {
        var tree = new BinarySearchTree();
        assertNull(tree.getRoot());

        tree.insert(50);
        var root = tree.getRoot();
        assertEquals(50, root.getKey());
        assertNull(root.getLeft());
        assertNull(root.getRight());

        tree.insert(40);
        tree.insert(70);
        assertEquals(40, root.getLeft().getKey());
        assertEquals(70, root.getRight().getKey());

        var firstLeft = root.getLeft();
        assertNull(firstLeft.getLeft());
        assertNull(firstLeft.getRight());

        System.out.println(printer.buildTreeDiagram(tree));
    }
}