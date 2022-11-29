package ru.otus.erinary.algo.trees;

/**
 * Узел дерева.
 */
public class TreeNode {

    private final int key;
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

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
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
