package net.coderodde.util.tree.support;

import net.coderodde.util.tree.BinaryTreeNode;

public final class DefaultBinaryTreePrinter {

    private static final int DEFAULT_TOP_PADDING = 0;
    private static final int DEFAULT_RIGHT_PADDING = 0;
    private static final int DEFAULT_BOTTOM_PADDING = 0;
    private static final int DEFAULT_LEFT_PADDING = 0;
    private static final int DEFAULT_MIN_SIBLING_SPACE = 1;
    
    /**
     * Returns a {@link String} representing the input binary tree rooted at
     * {@code root}.
     * 
     * @param <T>             The tree node value type.
     * @param root            The root node of the binary tree to print.
     * @param topPadding      The top border padding.
     * @param rightPadding    The right border padding.
     * @param bottomPadding   The bottom border padding.
     * @param leftPadding     The left border padding.
     * @param minSiblingSpace Given two immediate siblings, how man spaces they
     *                        are apart of each other.
     * 
     * @return a textual representation of the tree.
     */
    public static <T> String printBinaryTree(BinaryTreeNode<T> root,
                                             int topPadding,
                                             int rightPadding,
                                             int bottomPadding,
                                             int leftPadding,
                                             int minSiblingSpace) {
        return TextSprite.convertLeafNodeToTextSprite(root, 
                                                      topPadding,
                                                      topPadding, 
                                                      topPadding, 
                                                      topPadding).toString();
    }
    
    public static <T> String printBinaryTree(BinaryTreeNode<T> root) {
        return printBinaryTree(root, 
                               DEFAULT_TOP_PADDING,
                               DEFAULT_RIGHT_PADDING,
                               DEFAULT_BOTTOM_PADDING,
                               DEFAULT_LEFT_PADDING,
                               DEFAULT_MIN_SIBLING_SPACE);
    }
    
    private static final class TextSprite {
        
        private final int width;
        private final int height;
        private final char[][] sprite;
        
        private static final char CORNER_CHAR = '+';
        private static final char HORIZONTAL_BORDER_CHAR = '-';
        private static final char VERTICAL_BORDER_CHAR = '|';
        private static final char ARROW_CHAR = 'V';
        
        TextSprite(int width, int height) {
            this.width = width;
            this.height = height;
            this.sprite = new char[height][width];
        }
        
        static <T> 
        TextSprite convertLeafNodeToTextSprite(BinaryTreeNode<T> root,
                                               int paddingTop,
                                               int paddingRight,
                                               int paddingBottom,
                                               int paddingLeft) {
            String rawValue = root.getValue().toString();
            String[] lines = rawValue.split("\n");
            
            int longestLineLength = findLongestLineLength(lines);
            int width = 2 + paddingLeft + paddingRight + longestLineLength;
            int height = 2 + paddingTop + paddingBottom + lines.length;
            
            TextSprite textSprite = new TextSprite(width, height);
            drawBorder(textSprite);
            drawString(textSprite,
                       lines,
                       paddingTop,
                       paddingLeft);
            
            return textSprite;
        }
        
        private static void drawString(TextSprite textSprite,
                                       String[] lines,
                                       int paddingTop,
                                       int paddingLeft) {
            int startY = 1 + paddingTop;
            int startX = 1 + paddingLeft;
            
            for (int lineNumber = 0; 
                    lineNumber < lines.length; 
                    lineNumber++, startY++) {
                char[] charArray = lines[lineNumber].toCharArray();
                
                for (int i = 0; i < charArray.length; ++i) {
                    textSprite.sprite[startY][startX + i] = charArray[i];
                }
            }
        }
        
        private static void drawBorder(TextSprite textSprite) {
            int width = textSprite.width;
            int height = textSprite.height;
            
            // Draw corners:
            textSprite.sprite[0][0] = CORNER_CHAR;
            textSprite.sprite[0][width - 1] = CORNER_CHAR;
            textSprite.sprite[height - 1][0] = CORNER_CHAR;
            textSprite.sprite[height - 1][width - 1] = CORNER_CHAR;
            
            // Draw horizontal borders:
            for (int x = 1; x < width - 1; ++x) {
                textSprite.sprite[0][x] = HORIZONTAL_BORDER_CHAR;
                textSprite.sprite[height  - 1][x] = HORIZONTAL_BORDER_CHAR;
            }
            
            // Draw vertical borders:
            for (int y = 1; y < height - 1; ++y) {
                textSprite.sprite[y][0] = VERTICAL_BORDER_CHAR;
                textSprite.sprite[y][width - 1] = VERTICAL_BORDER_CHAR;
            }
        }
        
        private static int findLongestLineLength(String[] lines) {
            int maxLength = 0;
            
            for (String line : lines) {
                maxLength = Math.max(maxLength, line.length());
            }
            
            return maxLength;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(height * (width + 1) - 1);
            String newLine = "";
            
            for (int y = 0; y < height; ++y) {
                sb.append(newLine);
                newLine = "\n";
                
                for (int x = 0; x < width; ++x) {
                    char c = sprite[y][x];
                    sb.append(c != 0 ? c : ' ');
                }
            }
            
            return sb.toString();
        }
    }
}
