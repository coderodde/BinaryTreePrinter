package net.coderodde.util.tree.support;

import net.coderodde.util.tree.BinaryTreeNode;
import net.coderodde.util.tree.BinaryTreeNodePrinter;
import net.coderodde.util.tree.BinaryTreePrinter;
import net.coderodde.util.tree.TextSprite;

/**
 * Implements a default binary tree printer.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jul 6, 2017)
 * @param <T> the type of the data contained in the binary tree nodes.
 */
public final class DefaultBinaryTreePrinter<T> implements BinaryTreePrinter<T> {

    /**
     * When combining the text sprites of two sibling subtrees, by default, at 
     * least one character worth horizontal space will be put between the two
     * sprites.
     */
    private static final int DEFAULT_MINIMUM_SIBLING_SPACE = 1;
    
    /**
     * The default character for printing arrow tips.
     */
    private static final char DEFAULT_ARROW_TIP_CHARACTER = 'V';
    
    /**
     * The minimum number of spaces between two siblings.
     */
    private int siblingSpace;
            
    /**
     * The arrow tip character.
     */
    private char arrowTipCharacter;
    
    
    @Override
    public String print(BinaryTreeNode<T> root, 
                        BinaryTreeNodePrinter<T> nodePrinter) {
        return printImpl(root, nodePrinter).textSprite.toString();
    }

    public int getSiblingSpace() {
        return siblingSpace;
    }
    
    public char getArrowTipCharacter() {
        return arrowTipCharacter;
    }
    
    public void setSiblingSpace(int siblingSpace) {
        this.siblingSpace = checkSiblingSpace(siblingSpace);
    }
    
    public void setArrowTipCharacter(char arrowTipCharacter) {
        this.arrowTipCharacter = arrowTipCharacter;
    }
    
    private static final class SubtreeDescriptor {
        
        TextSprite textSprite;
        int rootNodeOffset;
        int rootNodeWidth;
    }
    
    private SubtreeDescriptor printImpl(BinaryTreeNode<T> node, 
                                        BinaryTreeNodePrinter<T> nodePrinter) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            TextSprite leafNodeTextSprite = nodePrinter.print(node);
            SubtreeDescriptor subtreeDescriptor = new SubtreeDescriptor();
            subtreeDescriptor.rootNodeOffset = 0;
            subtreeDescriptor.rootNodeWidth = leafNodeTextSprite.getWidth();
            subtreeDescriptor.textSprite = leafNodeTextSprite;
            return subtreeDescriptor;
        }
        
        if (node.getLeftChild() != null && node.getRightChild() != null) {
            return printWithTwoChildrenImpl(node, nodePrinter);
        }
        
        if (node.getLeftChild() != null) {
            return printWithLeftChildImpl(node, nodePrinter);
        }
        
        return printWithRightChildImpl(node, nodePrinter);
    }
    
    private SubtreeDescriptor printWithTwoChildrenImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        SubtreeDescriptor subtreeDescriptor = new SubtreeDescriptor();
        SubtreeDescriptor leftChildDescriptor = printImpl(node.getLeftChild(),
                                                          nodePrinter);
        
        SubtreeDescriptor rightChildDescriptor = printImpl(node.getRightChild(),
                                                           nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        TextSprite subtreeTextSprite = new TextSprite(0, 0);
        subtreeDescriptor.rootNodeOffset = 0;
        subtreeDescriptor.rootNodeWidth = nodeTextSprite.getWidth();
        subtreeDescriptor.textSprite = subtreeTextSprite;
        // Combine them:
        return null;
    }
    
    private SubtreeDescriptor printWithLeftChildImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        SubtreeDescriptor subtreeDescriptor = new SubtreeDescriptor();
        SubtreeDescriptor leftChildDescriptor = printImpl(node.getLeftChild(),
                                                          nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        TextSprite subtreeTextSprite = new TextSprite(0, 0);
        subtreeDescriptor.rootNodeOffset = 0;
        subtreeDescriptor.rootNodeWidth = nodeTextSprite.getWidth();
        subtreeDescriptor.textSprite = subtreeTextSprite;
        return subtreeDescriptor;
    }
    
    private SubtreeDescriptor printWithRightChildImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        SubtreeDescriptor subtreeDescriptor = new SubtreeDescriptor();
        SubtreeDescriptor rightChildDescriptor = printImpl(node.getRightChild(),
                                                           nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        TextSprite subtreeTextSprite = new TextSprite(0, 0);
        subtreeDescriptor.rootNodeOffset = 0;
        subtreeDescriptor.rootNodeWidth = nodeTextSprite.getWidth();
        subtreeDescriptor.textSprite = subtreeTextSprite;
        return subtreeDescriptor;
    }

    private int checkSiblingSpace(int siblingSpace) {
        if (siblingSpace < 0) {
            throw new IllegalArgumentException("Sibling space is negative: " +
                    siblingSpace);
        }
        
        return siblingSpace;
    }
}
