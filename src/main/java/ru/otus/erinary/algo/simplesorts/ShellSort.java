package ru.otus.erinary.algo.simplesorts;

/**
 * Сортировка Шелла.
 * <p>
 * Сравниваются элементы не только стоящие рядом, но и на определенном расстоянии друг от друга.
 */
public class ShellSort extends SimpleSort {

    /**
     * Выполняет сортировку переданного массива.
     *
     * @param array массив чисел
     */
    public void sort(final int[] array) {
        startWatch();
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j >= gap && array[j - gap] > array[j]; j -= gap) {
                    swap(array, j, j - gap);
                }
            }
        }
        stopWatch();
    }
}
