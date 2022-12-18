package ru.otus.erinary.algo.trees;

import java.util.Optional;

/**
 * Узел дерева.
 */
public class TreeNode {

    private int key;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private int height;

    /**
     * Возвращает экземпляр {@link TreeNode}.
     *
     * @param key ключ узла
     */
    public TreeNode(final int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(final int key) {
        this.key = key;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(final TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(final TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(final TreeNode right) {
        this.right = right;
    }

    /**
     * Добавляет левую или правую (в зависимости от значения ключа) доечернюю ноду.
     *
     * @param child новая дочерняя нода
     */
    public void addChild(final TreeNode child) {
        Optional.ofNullable(child).ifPresent(c -> {
            if (c.getKey() < key) {
                left = c;
            } else if (c.getKey() > key) {
                right = c;
            }
        });
    }

    public int getHeight() {
        return height;
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
