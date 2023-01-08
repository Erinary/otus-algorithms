package ru.otus.erinary.algo.trees;

import ru.otus.erinary.algo.trees.nodes.BinaryTreeNode;

/**
 * Утилитарный класс, который строит диаграмму бинарного дерева. Формат узла: 'ключ (высота)'
 */
public class BinaryTreePrinter {

    /**
     * Строит диаграмму бинарного дерева.
     *
     * @param tree бинарное дерево {@link BinarySearchTree}
     */
    public String buildTreeDiagram(final BinaryTree tree) {
        return traversePreOrder(tree.getRoot());
    }

    private String traversePreOrder(final BinaryTreeNode root) {

        if (root == null) {
            return "";
        }

        var sb = new StringBuilder();
        sb.append(root.getKey()).append("(").append(root.getHeight()).append(")");

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    private void traverseNodes(final StringBuilder sb, final String padding, final String pointer, final BinaryTreeNode node,
                               final boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getKey()).append("(").append(node.getHeight()).append(")");

            var paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            var paddingForBoth = paddingBuilder.toString();
            var pointerRight = "└──";
            var pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
}
