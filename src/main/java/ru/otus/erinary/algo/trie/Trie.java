package ru.otus.erinary.algo.trie;

/**
 * Префиксное дерево. Использует строки в качестве ключей, в каждой ноде хранится массив символов, ключи складываются в
 * ноды по символам, и т. о., каждому ключу соответствует своя "цепочка" нод.
 */
public class Trie {

    private static final int DEFAULT_SIZE = 128;
    private final int arraySize;
    private final TrieNode root;

    public Trie() {
        this(DEFAULT_SIZE);
    }

    /**
     * Создает экземпляр {@link Trie}.
     *
     * @param arraySize размер внутреннего символьного массива.
     */
    public Trie(final int arraySize) {
        this.arraySize = arraySize;
        this.root = new TrieNode();
    }

    /**
     * Вставляет новый ключ.
     *
     * @param word ключ
     */
    public void insert(final String word) {
        var node = root;
        for (char c : word.toCharArray()) {
            node = node.next(c);
        }
        node.isEnd = true;
    }

    /**
     * Поиск переданного ключа.
     *
     * @param word ключ
     * @return true, если такой ключ присутствует в дереве
     */
    public boolean search(final String word) {
        var node = root;
        for (char c : word.toCharArray()) {
            if (node.exists(c)) {
                node = node.children[c];
            } else {
                return false;
            }
        }
        return node.isEnd;
    }

    /**
     * Проверяет, есть ли в дереве ключ, начинающийся с переданного префикса.
     *
     * @param prefix префикс
     * @return true, если подходящий ключ присутствует в дереве
     */
    public boolean startsWith(final String prefix) {
        var node = root;
        for (char c : prefix.toCharArray()) {
            if (node.exists(c)) {
                node = node.children[c];
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Нода префиксного дерева.
     */
    private class TrieNode {

        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            this.children = null;
            this.isEnd = false;
        }

        /**
         * Возвращает дочернюю ноду, соответствующую переданному символу, если такой нет - создает ее.
         *
         * @param c символ
         * @return нода, соответствующая символу
         */
        public TrieNode next(final char c) {
            if (children == null) {
                children = new TrieNode[arraySize];
            }
            if (!exists(c)) {
                children[c] = new TrieNode();
            }
            return children[c];
        }

        /**
         * Проверяет, существует ли дочерняя нода, соответствующая переданному символу.
         *
         * @param c символ
         * @return true, если такая нода есть
         */
        public boolean exists(final char c) {
            return children != null && children[c] != null;
        }
    }

}
