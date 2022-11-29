package ru.otus.erinary.algo.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreePrinterTest {

    @Test
    void printTree() {
        var printer = new BinaryTreePrinter();
        var tree = new BinarySearchTree();
        assertEquals("", printer.buildTreeDiagram(tree));

        tree.insert(50);
        assertEquals("50(0)", printer.buildTreeDiagram(tree));

        tree.insert(40);
        tree.insert(70);
        assertEquals("50(0)\n├──40(1)\n└──70(1)", printer.buildTreeDiagram(tree));

        tree.insert(39);
        tree.insert(72);
        tree.insert(45);
        tree.insert(49);
        assertEquals("50(0)\n├──40(1)\n│  ├──39(2)\n│  └──45(2)\n│     └──49(3)\n└──70(1)\n   └──72(2)",
                printer.buildTreeDiagram(tree));
    }
}