package ru.otus.erinary.algo.trees.nodes;

import java.util.Optional;

/**
 * Узел бинарного дерева.
 */
public class BinaryTreeNode {

    private int key;
    private BinaryTreeNode parent;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private int height;
    private int weight;

    public BinaryTreeNode() {
    }

    /**
     * Возвращает экземпляр {@link BinaryTreeNode}.
     *
     * @param key ключ узла
     */
    public BinaryTreeNode(final int key) {
        this.key = key;
    }

    /**
     * Возвращает экземпляр {@link BinaryTreeNode}.
     *
     * @param key    ключ узла
     * @param weight вес узла
     */
    public BinaryTreeNode(final int key, final int weight) {
        this.key = key;
        this.weight = weight;
    }

    public int getKey() {
        return key;
    }

    public void setKey(final int key) {
        this.key = key;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(final BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(final BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(final BinaryTreeNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(final int weight) {
        this.weight = weight;
    }

    /**
     * Добавляет левую или правую (в зависимости от значения ключа) доечернюю ноду.
     *
     * @param child новая дочерняя нода
     */
    public void addChild(final BinaryTreeNode child) {
        Optional.ofNullable(child).ifPresent(c -> {
            if (c.getKey() < key) {
                left = c;
            } else if (c.getKey() > key) {
                right = c;
            }
        });
    }

    /**
     * Подсчитывает высоту (расстояние до корня дерева) данного узла. Если высота равна 0, то текущий узел - корень.
     */
    public void recalculateHeight() {
        int height = 0;
        var cursor = parent;
        while (cursor != null) {
            height++;
            cursor = cursor.getParent();
        }
        this.height = height;
    }
}
