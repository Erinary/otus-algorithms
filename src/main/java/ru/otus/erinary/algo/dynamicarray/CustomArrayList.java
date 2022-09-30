package ru.otus.erinary.algo.dynamicarray;

/**
 * Интерфейс динамического списка.
 */
public interface CustomArrayList<T> {

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    int size();

    /**
     * Возвращает флаг, содержит ли список элементы.
     *
     * @return true, если список пустой.
     */
    boolean isEmpty();

    /**
     * Получение элемента по индексу.
     *
     * @param index индекс элемента
     * @return элемент списка
     */
    T get(int index);

    /**
     * Помещает элемент в список.
     *
     * @param item элемент
     */
    void put(T item);

    /**
     * Помещает элемент по указанному индексу
     *
     * @param item  элемент
     * @param index индекс
     */
    void put(T item, int index);

    /**
     * Удаляет указанный элемент.
     *
     * @param index индекс элемента
     * @return удаленный элемент
     */
    T remove(int index);

}
