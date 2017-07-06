package net.coderodde.util.tree.demo;

import net.coderodde.util.tree.BinaryTreeNode;

public final class SimpleStringBinaryTreeNode implements BinaryTreeNode<String> {

    public String value;
    public SimpleStringBinaryTreeNode leftChild;
    public SimpleStringBinaryTreeNode rightChild;
    
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public BinaryTreeNode<String> getLeftChild() {
        return leftChild;
    }

    @Override
    public BinaryTreeNode<String> getRightChild() {
        return rightChild;
    }
}
