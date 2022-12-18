package ru.otus.erinary.algo.trees;

import java.util.Optional;

/**
 * Двоичное дерево поиска.
 */
public class BinarySearchTree implements BinaryTree {

    private TreeNode root;

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public void insert(final int item) {
        root = insertInternal(root, null, item);
    }

    private TreeNode insertInternal(final TreeNode node, final TreeNode parent, final int item) {
        if (node == null) {
            var current = new TreeNode(item);
            current.setParent(parent);
            current.recalculateHeight();
            return current;
        }

        if (node.getKey() > item) {
            node.setLeft(insertInternal(node.getLeft(), node, item));
        } else if (node.getKey() < item) {
            node.setRight(insertInternal(node.getRight(), node, item));
        }
        return node;
    }

    @Override
    public boolean search(final int item) {
        return internalSearch(root, item) != null;
    }

    private TreeNode internalSearch(final TreeNode node, final int item) {
        if (node == null || node.getKey() == item) {
            return node;
        }
        return item < node.getKey() ? internalSearch(node.getLeft(), item) : internalSearch(node.getRight(), item);
    }

    @Override
    public void remove(final int item) {
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
    public static TreeNode findMax(final TreeNode root) {
        return root.getRight() == null ? root : findMax(root.getRight());
    }

}
