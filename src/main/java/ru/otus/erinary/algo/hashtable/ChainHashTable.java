package ru.otus.erinary.algo.hashtable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Хэш-таблица, основанная на методе цепочек. При вставке для ключа вычисляется хэш-код, по которому потом определяется
 * позиция сохранения пары "ключ-значение" во внутреннем массиве. Суть метода цепочек: пары хранятся в массиве в виде
 * связанного списка, т. к. при вычислении хэша или индекса в массиве могут случаться коллизии. При достижении определенного
 * количества элементов в таблице происходит увеличение размера массива и рехэширование.
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public class ChainHashTable<K, V> implements HashTable<K, V>, Iterable<ChainHashTable.ChainHashEntry<K, V>> {

    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float loadFactor;
    private int threshold;
    private int size;
    private ChainHashEntry<K, V>[] buckets;

    public ChainHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public ChainHashTable(final int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public ChainHashTable(final int capacity, final float loadFactor) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity mustn't be less or equal 0");
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("Load Factor mustn't be less or equal 0");
        }
        this.buckets = (ChainHashEntry<K, V>[]) new ChainHashEntry[capacity];
        this.loadFactor = loadFactor;
        this.threshold = (int) (capacity * loadFactor);
    }


    @Override
    public V get(final K key) {
        int index = hash(key);
        var entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V put(final K key, final V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int index = hash(key);
        var entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            } else {
                entry = entry.next;
            }
        }
        if (++size > threshold) {
            rehash();
            index = hash(key);
        }
        entry = new ChainHashEntry<>(key, value);
        entry.next = buckets[index];
        buckets[index] = entry;
        return null;
    }

    @Override
    public V remove(final K key) {
        int index = hash(key);
        var entry = buckets[index];
        ChainHashEntry<K, V> last = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                if (last == null) {
                    buckets[index] = entry.next;
                } else {
                    last.next = entry.next;
                }
                size--;
                return entry.value;
            }
            last = entry;
            entry = entry.next;
        }
        return null;
    }

    /**
     * Сложность операции - O(N).
     */
    @Override
    public boolean contains(final V value) {
        if (value == null) {
            throw new NullPointerException();
        }
        for (int i = buckets.length - 1; i >= 0; i--) {
            var entry = buckets[i];
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    /**
     * Сложность операции - O(N) в худшем случае, в лучшем O(1).
     */
    @Override
    public boolean containsKey(final K key) {
        int index = hash(key);
        var entry = buckets[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    /**
     * Возвращает количество элементов в таблице.
     *
     * @return количество элементов в таблице
     */
    public int getSize() {
        return size;
    }

    /**
     * Признак, пустая ли таблица.
     *
     * @return true, если таблица пустая
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Находит индекс в массиве пар "ключ-значение" для переданного ключа на основе его хэш-кода.
     *
     * @param key ключ
     * @return индекс в массиве пар "ключ-значение"
     */
    private int hash(final K key) {
        int hash = key.hashCode() % buckets.length;
        return hash < 0 ? -hash : hash;
    }

    /**
     * Увеличивает вместимость таблицы, пересоздавая внутренний массив. Элементы внутри табицы размещаются заново,
     * т. к. хэши ключей перерасчитываются.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        var oldBuckets = buckets;
        int newCapacity = (buckets.length * 2) + 1;
        threshold = (int) (newCapacity * loadFactor);
        buckets = (ChainHashEntry<K, V>[]) new ChainHashEntry[newCapacity];

        for (int i = oldBuckets.length - 1; i >= 0; i--) {
            var entry = oldBuckets[i];
            while (entry != null) {
                int index = hash(entry.key);
                var destination = buckets[index];
                if (destination != null) {
                    var next = destination.next;
                    while (next != null) {
                        destination = next;
                        next = destination.next;
                    }
                    destination.next = entry;
                } else {
                    buckets[index] = entry;
                }
                var next = entry.next;
                entry.next = null;
                entry = next;
            }
        }
    }

    @Override
    public Iterator<ChainHashEntry<K, V>> iterator() {
        return new HashTableIterator(this);
    }

    @Override
    public String toString() {
        int max = size - 1;
        if (max == -1) {
            return "[]";
        }
        var sb = new StringBuilder();
        var itr = new ChainHashTable<K, V>.HashTableIterator(this);
        sb.append("{");
        for (int i = 0; ; i++) {
            var entry = itr.next();
            sb.append("(").append(itr.getCurrentIndex()).append(")").append(":").append("[")
                    .append(entry.key.toString()).append(", ").append(entry.value.toString()).append("]");
            if (i == max) {
                sb.append("}");
                return sb.toString();
            }
            sb.append(", ").append(System.lineSeparator());
        }
    }

    /**
     * Внутренний элемент хэш-таблицы, пара "ключ-значение".
     *
     * @param <K> тип ключа
     * @param <V> тип значения
     */
    protected static class ChainHashEntry<K, V> {
        private final K key;
        private V value;
        private ChainHashEntry<K, V> next;

        public ChainHashEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(final V value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.value = value;
        }

        public ChainHashEntry<K, V> getNext() {
            return next;
        }

    }

    /**
     * Реализация {@link Iterator}.
     */
    private class HashTableIterator implements Iterator<ChainHashEntry<K, V>> {

        private final ChainHashTable<K, V> table;
        private int cursor;
        private int currentIndex;
        private ChainHashEntry<K, V> currentEntry;

        public HashTableIterator(final ChainHashTable<K, V> table) {
            this.table = table;
            this.currentIndex = this.table.buckets.length;
        }

        @Override
        public boolean hasNext() {
            return cursor < table.size;
        }

        @Override
        public ChainHashEntry<K, V> next() {
            ChainHashEntry<K, V> entry = currentEntry;
            int i = currentIndex;
            while (entry == null && i > 0) {
                entry = table.buckets[--i];
            }
            currentEntry = entry;
            currentIndex = i;
            if (entry != null) {
                currentEntry = entry.next;
                cursor++;
                return entry;
            }
            throw new NoSuchElementException();
        }

        public int getCurrentIndex() {
            return currentIndex;
        }
    }
}
