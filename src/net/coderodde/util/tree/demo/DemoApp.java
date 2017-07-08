package net.coderodde.util.tree.demo;

import java.util.Scanner;
import net.coderodde.util.tree.BinaryTreeNode;
import net.coderodde.util.tree.support.DefaultBinaryTreeNodePrinter;
import net.coderodde.util.tree.support.DefaultBinaryTreePrinter;

public final class DemoApp {

    private static final 
            class SimpleBinaryTree<T extends Comparable<? super T>> {
        
        private static final 
                class SimpleBinaryTreeNode<T extends Comparable<? super T>> 
                implements BinaryTreeNode<T>{
            T value;
            SimpleBinaryTreeNode<T> parent;
            SimpleBinaryTreeNode<T> left;
            SimpleBinaryTreeNode<T> right;

            @Override
            public T getValue() {
                return value;
            }

            @Override
            public BinaryTreeNode<T> getLeftChild() {
                return left;
            }

            @Override
            public BinaryTreeNode<T> getRightChild() {
                return right;
            }
            
        }
        
        private SimpleBinaryTreeNode<T> root;
        
        SimpleBinaryTreeNode<T> getRoot() {
            return root;
        }
        
        void add(T value) {
            if (root == null) {
                root = new SimpleBinaryTreeNode<>();
                root.value = value;
                return;
            }
            
            SimpleBinaryTreeNode<T> parent = null;
            SimpleBinaryTreeNode<T> node = root;
            
            while (node != null) {
                parent = node;
                
                int cmp = value.compareTo(node.value);
                
                if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                } else {
                    return;
                }
            }
            
            int cmp = value.compareTo(parent.value);
            SimpleBinaryTreeNode<T> newNode = new SimpleBinaryTreeNode<>();
            newNode.value = value;
            
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            
            newNode.parent = parent;
        }
        
        void remove(T value) {
            if (root == null) {
                return;
            }
            
            SimpleBinaryTreeNode<T> node = root;
            
            while (node != null) {
                int cmp = value.compareTo(node.value);
                
                if (cmp < 0) {
                    node = node.left;
                } else if (cmp > 0) {
                    node = node.right;
                } else {
                    break;
                }
            }
            
            if (node == null) {
                return;
            }
            
            doRemove(node);
        }
        
        private SimpleBinaryTreeNode<T> min(SimpleBinaryTreeNode<T> node) {
            while (node.left != null) {
                node = node.left;
            }
            
            return node;
        }
        
        private void doRemove(SimpleBinaryTreeNode<T> node) {
            if (node.left == null && node.right == null) {
                if (node.parent == null) {
                    root = null;
                    return;
                }
                
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                
                return;
            }
            
            if (node.left != null && node.right != null) {
                SimpleBinaryTreeNode<T> successor = min(node.right);
                node.value = successor.value;
                doRemove(successor);
                return;
            }
            
            if (node.left != null) {
                SimpleBinaryTreeNode<T> parent = node.parent;
                
                if (parent == null) {
                    root = node.left;
                    node.left.parent = null;
                } else {
                    if (parent.left == node) {
                        parent.left = node.left;
                        node.left.parent = parent;
                    } else {
                        parent.right = node.left;
                        node.left.parent = parent;
                    }    
                }
                
                return;
            }
            
            if (node.right != null) {
                SimpleBinaryTreeNode<T> parent = node.parent;
                
                if (parent == null) {
                    root = node.right;
                    node.right.parent = null;
                } else {
                    if (parent.left == node) {
                        parent.left = node.right;
                        node.right.parent = parent;
                    } else {
                        parent.right = node.right;
                        node.right.parent = parent;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        DefaultBinaryTreeNodePrinter<Integer> nodePrinter = 
                new DefaultBinaryTreeNodePrinter<>();
        
        DefaultBinaryTreePrinter<Integer> printer = 
                new DefaultBinaryTreePrinter<>();
        
        printer.setSiblingSpace(2);
        printer.setArrowTipCharacter('|');
        
        SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String cmd = scanner.next();
            
            switch (cmd) {
                case "quit":
                    return;
                    
                case "add":
                    int item = scanner.nextInt();
                    tree.add(item);
                    
                    System.out.println(printer.print(tree.getRoot(), nodePrinter));
                    break;
                    
                case "rem":
                    item = scanner.nextInt();
                    tree.remove(item);
                    
                    System.out.println(printer.print(tree.getRoot(), nodePrinter));
                    break;
            }
        }
    }
}
