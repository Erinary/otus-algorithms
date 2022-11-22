package ru.otus.erinary.algo.sorting.linearsorts;

import ru.otus.erinary.algo.sorting.AbstractSort;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Блочная сортировка.
 * <p>
 * В алгоритме находится максимальный элемент. Элементы распределяются между конечным числом отдельных корзин так,
 * чтобы все элементы в каждом следующем по порядку блоке были всегда больше (или меньше), чем в предыдущем.
 * Каждый блок затем сортируется отдельно, либо рекурсивно тем же методом, либо другим. Затем элементы помещаются обратно в массив.
 * Сложность - O(N) в лучшем случае, O(N^2) в худшем.
 */
public class BucketSort extends AbstractSort {

    @Override
    public void sort(final int[] array) {
        watcher.startWatch();

        int maxElement = array[findIndexMax(array, array.length)];

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[array.length];
        for (int i = 0; i < array.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int j : array) {
            int bucketIndex = (j * array.length) / (maxElement + 1);
            var bucket = buckets[bucketIndex];
            bucket.add(j);
            if (bucket.size() > 1) {
                bucket.sort(Comparator.naturalOrder()); //uses mergesort (timsort)
            }
        }

        int count = 0;
        for (int k = 0; k < array.length; k++) {
            var bucket = buckets[k];
            for (int n : bucket) {
                array[count++] = n;
            }
        }

        watcher.stopWatch();
    }

}
