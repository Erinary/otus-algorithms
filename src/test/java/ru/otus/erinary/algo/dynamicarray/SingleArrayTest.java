package ru.otus.erinary.algo.dynamicarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleArrayTest {

    @Test
    void testPut() {
        var list = new SingleArray<Integer>();
        assertTrue(list.isEmpty());

        list.put(1);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("[1]", list.toString());
    }

    @Test
    void testPutByIndex() {
        var list = new SingleArray<Integer>();
        assertTrue(list.isEmpty());

        for (int i = 1; i <= 5; i++) {
            list.put(i);
        }
        assertEquals(5, list.size());
        assertEquals("[1, 2, 3, 4, 5]", list.toString());

        list.put(9, 2);
        assertEquals(6, list.size());
        assertEquals("[1, 2, 9, 3, 4, 5]", list.toString());
    }

    @Test
    void testGet() {
        var list = new SingleArray<Integer>();
        assertTrue(list.isEmpty());

        for (int i = 1; i <= 5; i++) {
            list.put(i);
        }
        assertEquals(5, list.size());
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
        assertEquals(4, list.get(3));
    }

    @Test
    void testRemove() {
        var list = new SingleArray<Integer>();
        assertTrue(list.isEmpty());

        for (int i = 1; i <= 5; i++) {
            list.put(i);
        }
        assertEquals(5, list.size());
        assertEquals("[1, 2, 3, 4, 5]", list.toString());

        assertEquals(3, list.remove(2));
        assertEquals(4, list.size());
        assertEquals("[1, 2, 4, 5]", list.toString());
    }

}