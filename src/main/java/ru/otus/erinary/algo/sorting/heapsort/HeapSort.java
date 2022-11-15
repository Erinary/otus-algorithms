package ru.otus.erinary.algo.sorting.heapsort;

import ru.otus.erinary.algo.sorting.AbstractSort;

/**
 * Пирамидальная сортировка.
 * <p>
 * Сортировка на основе структуры данных "куча" (Heap) - структура типа дерево, в которой выполняется свойство:
 * "ключ узла родителя >= ключу узла потомка этого родителя". Т. о., корневой узел кучи = элемент с максимальным ключом.
 * Предварительно алгоритм формирует кучу из массива для поиска максимального элемента за константное время. Далее на
 * каждой итерации максимальный элемент переносится в конец массива (отсортированную часть), а куча пересобирается.
 * Сложность - O(N*log(N)).
 */
public class HeapSort extends AbstractSort {

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    @Override
    public void sort(final int[] array) {
        watcher.startWatch();
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }
        for (int j = array.length - 1; j > 0; j--) {
            swap(array, 0, j);
            heapify(array, 0, j);
        }
        watcher.stopWatch();
    }

    private void heapify(final int[] array, final int root, final int size) {
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int x = root;
        if (left < size && array[left] > array[x]) {
            x = left;
        }
        if (right < size && array[right] > array[x]) {
            x = right;
        }
        if (x == root) {
            return;
        }
        swap(array, x, root);
        heapify(array, x, size);
    }

}
