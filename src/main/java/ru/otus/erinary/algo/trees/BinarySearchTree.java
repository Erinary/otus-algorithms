package ru.otus.erinary.algo.trees;

import ru.otus.erinary.algo.trees.nodes.BinaryTreeNode;

import java.util.Optional;

/**
 * Двоичное дерево поиска.
 */
public class BinarySearchTree implements BinaryTree {

    private BinaryTreeNode root;

    public BinarySearchTree() {
    }

    /**
     * Заполняет дерево элементами из переданного массива.
     *
     * @param array массив чисел
     */
    public BinarySearchTree(final int[] array) {
        for (int elem : array) {
            insert(elem);
        }
    }

    @Override
    public BinaryTreeNode getRoot() {
        return root;
    }

    @Override
    public void insert(final int item) {
        root = insertInternal(root, null, item, 0);
    }

    @Override
    public void insert(final int item, final int weight) {
        root = insertInternal(root, null, item, weight);
    }

    private BinaryTreeNode insertInternal(final BinaryTreeNode node, final BinaryTreeNode parent, final int item,
                                          final int weight) {
        if (node == null) {
            var current = new BinaryTreeNode(item);
            current.setWeight(weight);
            current.setParent(parent);
            current.recalculateHeight();
            return current;
        }

        if (node.getKey() > item) {
            node.setLeft(insertInternal(node.getLeft(), node, item, weight));
        } else if (node.getKey() < item) {
            node.setRight(insertInternal(node.getRight(), node, item, weight));
        }
        return node;
    }

    @Override
    public boolean search(final int item) {
        return internalSearch(root, item) != null;
    }

    private BinaryTreeNode internalSearch(final BinaryTreeNode node, final int item) {
        if (node == null || node.getKey() == item) {
            return node;
        }
        return item < node.getKey() ? internalSearch(node.getLeft(), item) : internalSearch(node.getRight(), item);
    }

    @Override
    public void delete(final int item) {
        var node = internalSearch(root, item);
        if (node == null) {
            return;
        }

        //node has 2 children
        if (node.getLeft() != null && node.getRight() != null) {
            var leftMax = findMax(node.getLeft());
            node.setKey(leftMax.getKey());

            var leftMaxParent = leftMax.getParent();
            if (leftMaxParent.getKey() < leftMax.getKey()) {
                leftMaxParent.setRight(null);
            } else {
                leftMaxParent.setLeft(null);
            }

        //node has 1 child
        } else if (node.getLeft() != null) {
            node.getLeft().setParent(node.getParent());
            node.getLeft().recalculateHeight();
            Optional.ofNullable(node.getParent()).ifPresent(p -> p.addChild(node.getLeft()));
        } else if (node.getRight() != null) {
            node.getRight().setParent(node.getParent());
            node.getRight().recalculateHeight();
            Optional.ofNullable(node.getParent()).ifPresent(p -> p.addChild(node.getRight()));

        //node has no children
        } else {
            Optional.ofNullable(node.getParent()).ifPresent(p -> {
                if (node.getKey() < p.getKey()) {
                    p.setLeft(null);
                } else {
                    p.setRight(null);
                }
            });
        }
    }

    /**
     * Находит ноду с максимальным значением в поддереве.
     *
     * @param root корень поддерева
     * @return нода с максимальным значением
     */
    public static BinaryTreeNode findMax(final BinaryTreeNode root) {
        return root.getRight() == null ? root : findMax(root.getRight());
    }

}
