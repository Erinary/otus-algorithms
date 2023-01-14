package ru.otus.erinary.algo.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainHashTableTest {

    @Test
    void testPut() {
        var table = new ChainHashTable<SimpleHashString, String>();

        assertTrue(table.isEmpty());
        table.put(new SimpleHashString("key"), "val1");
        assertFalse(table.isEmpty());
        assertEquals(1, table.getSize());

        table.put(new SimpleHashString("yek"), "val2");
        assertEquals(2, table.getSize());
        table.put(new SimpleHashString("pew"), "val3");
        assertEquals(3, table.getSize());
        System.out.println(table);
    }

    @Test
    void testGet() {
        var table = new ChainHashTable<SimpleHashString, String>();
        var key = new SimpleHashString("key");
        var value = "val1";

        assertTrue(table.isEmpty());
        table.put(key, value);
        assertFalse(table.isEmpty());

        assertEquals(value, table.get(key));
    }

    @Test
    void testRemove() {
        var table = new ChainHashTable<SimpleHashString, String>();
        var keyToRemove = new SimpleHashString("yek");
        var valueToRemove = "val2";

        assertTrue(table.isEmpty());
        table.put(new SimpleHashString("key"), "val1");
        table.put(keyToRemove, valueToRemove);
        table.put(new SimpleHashString("pew"), "val3");
        assertEquals(3, table.getSize());

        var removed = table.remove(keyToRemove);
        assertEquals(2, table.getSize());
        assertEquals(valueToRemove, removed);
    }

    @Test
    void testContains() {
        var table = new ChainHashTable<SimpleHashString, String>();
        var valueToSearch = "search";

        assertTrue(table.isEmpty());
        table.put(new SimpleHashString("key"), valueToSearch);
        table.put(new SimpleHashString("key1"), "val1");
        table.put(new SimpleHashString("key2"), "val2");
        assertEquals(3, table.getSize());
        assertTrue(table.contains(valueToSearch));
    }

    @Test
    void testContainsKey() {
        var table = new ChainHashTable<SimpleHashString, String>();
        var keyToSearch = new SimpleHashString("search");

        assertTrue(table.isEmpty());
        table.put(keyToSearch, "val");
        table.put(new SimpleHashString("key1"), "val1");
        table.put(new SimpleHashString("key2"), "val2");
        assertEquals(3, table.getSize());
        assertTrue(table.containsKey(keyToSearch));
    }

    @Test
    void testRehash() {
        var table = new ChainHashTable<SimpleHashString, String>(5);
        assertTrue(table.isEmpty());
        table.put(new SimpleHashString("mod"), "val1");
        table.put(new SimpleHashString("moi"), "val2");
        assertEquals("{(0):[moi, val2], \n" + "(0):[mod, val1]}", table.toString());

        table.put(new SimpleHashString("pew"), "val3");
        table.put(new SimpleHashString("pew-pew"), "val4");
        assertNotEquals("{(6):[moi, val2], \n (5):[pew-pew, val4], \n (2):[pew, val3], \n (1):[mod, val1]}",
                table.toString());
    }

    @Test
    void testSimpleHashString() {
        assertEquals(new SimpleHashString("key").hashCode(), new SimpleHashString("yek").hashCode());
    }

    private static class SimpleHashString {
        private final String value;

        private SimpleHashString(final String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for (char c : value.toCharArray()) {
                hash += Character.hashCode(c);
            }
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SimpleHashString) {
                SimpleHashString simpleStr = (SimpleHashString) obj;
                return value.equals(simpleStr.value);
            }
            return false;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}