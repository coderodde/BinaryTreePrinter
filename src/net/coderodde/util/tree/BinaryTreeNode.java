package net.coderodde.util.tree;

/**
 * This interface describes the API for binary tree nodes.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jul 6, 2017)
 * @param <T> the value type.
 */
public interface BinaryTreeNode<T> {

    /**
     * Returns the value of this node.
     * @return the value of this node.
     */
    public T getValue();
    
    /**
     * Returns the left child of this node or {@code null} if there is no such.
     * @return the left child.
     */
    public BinaryTreeNode<T> getLeftChild();
    
    /**
     * Returns the right child of this node or {@code null} if there is no such.
     * @return the right child.
     */
    public BinaryTreeNode<T> getRightChild();
}
