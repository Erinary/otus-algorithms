package ru.otus.erinary.algo.simplesorts;

/**
 * Сортировка пузырьком.
 * <p>
 * Алгоритм за каждую итерацию находит максимальный элемент (сравнивает два соседних) и перетаскивает его в
 * отсортированную часть.
 * Сложность - O(N^2).
 */
public class BubbleSort extends SimpleSort {

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    public void sort(final int[] array) {
        startWatch();
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        stopWatch();
    }

    /**
     * Оптимизированный метод - если во время обхода массива не было ни одного обмена элементов, то массив отсортирован.
     *
     * @param array массив чисел
     */
    public void optimizedSort(final int[] array) {
        startWatch();
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = array.length - 1; i > 0; i--) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1);
                    swapped = true;
                }
            }
        }
        stopWatch();
    }

}
