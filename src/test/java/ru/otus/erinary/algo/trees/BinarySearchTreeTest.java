package ru.otus.erinary.algo.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void search() {
        int [] elements = {30, 25, 33, 28, 35, 34, 20, 23};
        var tree = createTree(elements);

        assertFalse(tree.search(5));
        assertFalse(tree.search(40));
        assertTrue(tree.search(25));
        assertTrue(tree.search(34));
    }

    @Test
    void findMax() {
        int [] elements = {30, 25, 33, 28, 35, 34, 20, 23};
        var tree = createTree(elements);

        assertEquals(35, BinarySearchTree.findMax(tree.getRoot()).getKey());
        tree.insert(50);
        assertEquals(50, BinarySearchTree.findMax(tree.getRoot()).getKey());
    }

    @Test
    void delete() {
        int [] elements = {30, 25, 33, 28, 35, 34, 20, 23};
        var tree = createTree(elements);
        System.out.println(printer.buildTreeDiagram(tree));

        tree.delete(23);
        assertFalse(tree.search(23));
        System.out.println(printer.buildTreeDiagram(tree));

        tree.delete(35);
        assertFalse(tree.search(35));
        System.out.println(printer.buildTreeDiagram(tree));

        tree.delete(25);
        assertFalse(tree.search(25));
        System.out.println(printer.buildTreeDiagram(tree));

        tree.delete(30);
        assertFalse(tree.search(30));
        System.out.println(printer.buildTreeDiagram(tree));
    }

    private static BinarySearchTree createTree(int[] elements) {
        var tree = new BinarySearchTree();
        for (int item : elements) {
            tree.insert(item);
        }
        return tree;
    }
}