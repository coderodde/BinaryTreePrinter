package net.coderodde.util.tree;

/**
 * This interface defines the API for binary tree node printers.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jul 6, 2017)
 * @param <T> the type of the binary tree node values.
 */
public interface BinaryTreeNodePrinter<T> {

    public TextSprite print(BinaryTreeNode<T> node);
}
