package ru.otus.erinary.algo.trees;

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
        } else if (node.getKey() < item){
            node.setRight(insertInternal(node.getRight(), node, item));
        }
        return node;
    }

    @Override
    public boolean search(final int item) {
        return false;
    }

    @Override
    public void remove(final int item) {

    }
}
