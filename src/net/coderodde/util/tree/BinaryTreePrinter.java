package net.coderodde.util.tree;

public interface BinaryTreePrinter<T> {

    /**
     * Prints a binary tree with root node {@code root} using a particular node
     * printer.
     * 
     * @param root        the root node of the tree to print.
     * @param nodePrinter an implementation of the tree node printer.
     * @return the string representation of the tree.
     */
    public String print(BinaryTreeNode<T> root,
                        BinaryTreeNodePrinter<T> nodePrinter);
}
