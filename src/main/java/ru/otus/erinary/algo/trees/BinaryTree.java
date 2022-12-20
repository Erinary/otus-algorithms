package ru.otus.erinary.algo.trees;

/**
 * Интерейфс, описывающий основные операции бинарного дерева.
 */
public interface BinaryTree {

    /**
     * Возвращает корень дерева.
     *
     * @return корень дерева
     */
    TreeNode getRoot();

    /**
     * Вставка элемента. Сложность - log(N).
     *
     * @param item элемент
     */
    void insert(int item);

    /**
     * Поиск элемента. Сложность - log(N).
     *
     * @param item элемент
     * @return true, если элемент присутствует в дереве
     */
    boolean search(int item);

    /**
     * Удаление элемента. Сложность - log(N).
     *
     * @param item элемент
     */
    void delete(int item);
}
