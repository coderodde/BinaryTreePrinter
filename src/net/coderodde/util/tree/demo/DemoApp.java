package net.coderodde.util.tree.demo;

import net.coderodde.util.tree.BinaryTreePrinter;
import net.coderodde.util.tree.support.DefaultBinaryTreeNodePrinter;
import net.coderodde.util.tree.support.DefaultBinaryTreePrinter;

public final class DemoApp {

    public static void main(String[] args) {
        DefaultBinaryTreeNodePrinter<String> nodePrinter = 
                new DefaultBinaryTreeNodePrinter<>();
        
        DefaultBinaryTreePrinter<String> printer = 
                new DefaultBinaryTreePrinter<>();
        
        SimpleStringBinaryTreeNode node = new SimpleStringBinaryTreeNode();
        SimpleStringBinaryTreeNode nodeRight = new SimpleStringBinaryTreeNode();
        SimpleStringBinaryTreeNode nodeRightLeft = new SimpleStringBinaryTreeNode();
        SimpleStringBinaryTreeNode nodeLeft = new SimpleStringBinaryTreeNode();
        SimpleStringBinaryTreeNode nodeLeftRight = new SimpleStringBinaryTreeNode();
        
        node.value = "Oh\nyeah!";
        nodeRight.value = "Hurraa!";
        nodeRightLeft.value = "How's it going?";
        nodeLeft.value = "Comm'on ! :)";
//        nodeLeftRight.value = "Joulu\npukkeaahhaki";
        nodeLeftRight.value = "abcdefGH";
//        
//        node.rightChild = nodeRight;
//        nodeRight.leftChild = nodeRightLeft;
//        node.leftChild = nodeLeft;
//        nodeLeft.rightChild = nodeLeftRight;
        nodeLeft.rightChild = nodeLeftRight;
        
        System.out.println(printer.print(nodeLeft, nodePrinter));
//        System.out.println(printer.print(node, nodePrinter));
        
//        printer.setTopLeftCornerCharacter('1');
//        printer.setTopRightCornerCharacter('2');
//        printer.setBottomRightCornerCharacter('3');
//        printer.setBottomLeftCornerCharacter('4');
//        
//        printer.setTopBorderCharacter('A');
//        printer.setRightBorderCharacter('B');
//        printer.setBottomBorderCharacter('C');
//        printer.setLeftBorderCharacter('D');
//        
//        printer.setTopPadding(0);
//        printer.setRightPadding(1);
//        printer.setBottomPadding(2);
//        printer.setLeftPadding(3);
        
    }
}
