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
        return printImpl(root, nodePrinter).toString();
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
    
    private TextSprite printImpl(BinaryTreeNode<T> node, 
                                 BinaryTreeNodePrinter<T> nodePrinter) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return nodePrinter.print(node);
        }
        
        if (node.getLeftChild() != null && node.getRightChild() != null) {
            return printWithTwoChildrenImpl(node, nodePrinter);
        }
        
        if (node.getLeftChild() != null) {
            return printWithLeftChildImpl(node, nodePrinter);
        }
        
        return printWithRightChildImpl(node, nodePrinter);
    }
    
    private TextSprite printWithTwoChildrenImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        TextSprite leftChildTextSprite = printImpl(node.getLeftChild(),
                                                   nodePrinter);
        
        TextSprite rightChildTextSprite = printImpl(node.getRightChild(),
                                                    nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        
        // Combine them:
        return null;
    }
    
    private TextSprite printWithLeftChildImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        TextSprite leftChildTextSprite = printImpl(node.getLeftChild(),
                                                   nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        
        return null;
    }
    
    private TextSprite printWithRightChildImpl(
            BinaryTreeNode<T> node,
            BinaryTreeNodePrinter<T> nodePrinter) {
        TextSprite rightChildTextSprite = printImpl(node.getRightChild(),
                                                    nodePrinter);
        
        TextSprite nodeTextSprite = nodePrinter.print(node);
        
        return null;
    }

    private int checkSiblingSpace(int siblingSpace) {
        if (siblingSpace < 0) {
            throw new IllegalArgumentException("Sibling space is negative: " +
                    siblingSpace);
        }
        
        return siblingSpace;
    }
}
