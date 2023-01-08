package ru.otus.erinary.algo.trees.optimal;

import org.apache.commons.lang3.tuple.Pair;
import ru.otus.erinary.algo.trees.BinarySearchTree;
import ru.otus.erinary.algo.trees.nodes.BinaryTreeNode;

import java.util.*;

/**
 * Оптимальное бинарное дерево поиска. Статичное, т. к. не может быть изменено после создания.
 */
public class OptimalStaticBST extends BinarySearchTree implements Iterable<BinaryTreeNode> {

    private int weight;
    private double height;

    /**
     * Возвращает экземпляр {@link OptimalStaticBST}.
     *
     * @param array набор ключей
     * @implNote каждой ноде будет присвоен вес 0
     */
    public OptimalStaticBST(final int[] array) {
        var pairs = new ArrayList<Pair<Integer, Integer>>();
        for (int i : array) {
            pairs.add(Pair.of(i, 0));
        }
        new OptimalStaticBST(pairs, BuildingStrategy.KEY_SORTING);
    }

    /**
     * Возвращает экземпляр {@link OptimalStaticBST}.
     *
     * @param pairs    список пар {@link Pair}, в которых <L> - ключ ноды, <R> - ее вес
     * @param strategy стратегия построения дерева оптимального поиска
     */
    public OptimalStaticBST(final List<Pair<Integer, Integer>> pairs, final BuildingStrategy strategy) {
        switch (strategy) {
            case WEIGHT_SORTING:
                pairs.sort((p1, p2) -> p2.getRight() - p1.getRight());
                pairs.forEach(p -> super.insert(p.getLeft(), p.getRight()));
                this.weight = calculateWeight();
                this.height = calculateHeight();
                break;
            case KEY_SORTING:
                recursiveMehlhornAlgo(pairs, 0, pairs.size());
                this.weight = calculateWeight();
                this.height = calculateHeight();
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void recursiveMehlhornAlgo(final List<Pair<Integer, Integer>> pairs, final int first, final int last) {
        int sum = 0;
        int weight = 0;
        if (first < last) {
            for (int i = first; i < last; i++) {
                weight += pairs.get(i).getRight();
            }
            int current = first;
            for (; current < last; current++) {
                var node = pairs.get(current);
                if (sum < weight / 2 && sum + node.getRight() > weight / 2) {
                    sum += node.getRight();
                } else {
                    super.insert(node.getLeft(), node.getRight());
                    break;
                }
            }
            recursiveMehlhornAlgo(pairs, first, current);
            recursiveMehlhornAlgo(pairs, current + 1, last);
        }
    }

    public int getWeight() {
        return weight;
    }

    private int calculateWeight() {
        var weight = 0;
        for (BinaryTreeNode binaryTreeNode : this) {
            weight += binaryTreeNode.getWeight();
        }
        return weight;
    }

    public double getHeight() {
        return height;
    }

    private double calculateHeight() {
        if (weight == 0) {
            weight = calculateWeight();
        }
        var height = 0;
        for (BinaryTreeNode node : this) {
            height += node.getWeight() * (node.getHeight() + 1);
        }
        return height / (double) weight;
    }

    @Override
    public void insert(final int item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(final int item, final int weight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(final int item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<BinaryTreeNode> iterator() {
        return new TreeIterator();
    }

    /**
     * Реализация {@link Iterator} с обходом дерева в ширину.
     */
    private class TreeIterator implements Iterator<BinaryTreeNode> {

        private final Queue<BinaryTreeNode> queue;

        public TreeIterator() {
            this.queue = new LinkedList<>();
            queue.offer(getRoot());
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public BinaryTreeNode next() {
            var node = Optional.ofNullable(queue.poll()).orElseThrow(NoSuchElementException::new);
            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.offer(node.getRight());
            }
            return node;
        }
    }

    /**
     * Стратегия построения дерева оптимального поиска.
     */
    public enum BuildingStrategy {
        /**
         * Алгоритм 1: ноды упорядочиваются по убыванию веса, далее заполняется BST.
         */
        WEIGHT_SORTING,

        /**
         * Алгоритм 2: ноды упорядочиваются по возрастанию ключей, далее выбирается нода, для которой суммы весов левого
         * и правого поддеревьев различаются минимально.
         */
        KEY_SORTING
    }


}
