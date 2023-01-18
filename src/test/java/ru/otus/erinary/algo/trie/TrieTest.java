package ru.otus.erinary.algo.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ДЗ-13 - Префиксное дерево.
 */
class TrieTest {

    @Test
    void trieSearchTest() {
        var trie = new Trie();
        assertFalse(trie.search("a"));

        trie.insert("apple");
        assertFalse(trie.search("a"));
        assertFalse(trie.search("app"));
        assertTrue(trie.search("apple"));
    }

    @Test
    void trieStartWithTest() {
        var trie = new Trie();
        assertFalse(trie.startsWith("a"));

        trie.insert("apple");
        assertTrue(trie.startsWith("apple"));
        assertTrue(trie.startsWith("app"));
        assertTrue(trie.startsWith("a"));
    }

}