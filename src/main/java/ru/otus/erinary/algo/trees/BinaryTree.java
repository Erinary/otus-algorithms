package ru.otus.erinary.algo.trees;

import ru.otus.erinary.algo.trees.nodes.BinaryTreeNode;

/**
 * Интерейфс, описывающий основные операции бинарного дерева.
 */
public interface BinaryTree {

    /**
     * Возвращает корень дерева.
     *
     * @return корень дерева
     */
    BinaryTreeNode getRoot();

    /**
     * Вставка элемента. Сложность - log(N).
     *
     * @param item элемент
     */
    void insert(int item);

    /**
     * Вставка элемента. Сложность - log(N).
     *
     * @param item   элемент
     * @param weight вес элемента
     */
    void insert(int item, int weight);

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
