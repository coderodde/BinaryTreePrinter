package net.coderodde.util.tree.demo;

import net.coderodde.util.tree.BinaryTreeNode;

final class SimpleStringBinaryTreeNode implements BinaryTreeNode<String> {

    String value;
    SimpleStringBinaryTreeNode leftChild;
    SimpleStringBinaryTreeNode rightChild;
    
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
