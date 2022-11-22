package ru.otus.erinary.algo.sorting.linearsorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LinearSortTest {

    @Test
    void testBucketSort() {
        int[] array = {2, 7, 1, 3, 9, 8, 4};
        int[] expected = {1, 2, 3, 4, 7, 8, 9};

        var sort = new BucketSort();
        sort.sort(array);

        assertArrayEquals(array, expected);
    }

    @Test
    void testCountingSort() {
        int[] array = {2, 7, 1, 1, 3, 9, 3, 8, 4};
        int[] expected = {1, 1, 2, 3, 3, 4, 7, 8, 9};

        var sort = new CountingSort();
        sort.sort(array);

        assertArrayEquals(array, expected);
    }

    @Test
    void testRadixSort() {
        int[] array = {209, 3, 48, 91, 66, 101, 30, 795};
        int[] expected = {3, 30, 48, 66, 91, 101, 209, 795};

        var sort = new RadixSort();
        sort.sort(array);

        assertArrayEquals(array, expected);
    }

}