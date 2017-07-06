package net.coderodde.util.tree.support;

import net.coderodde.util.tree.BinaryTreeNode;
import net.coderodde.util.tree.BinaryTreeNodePrinter;
import net.coderodde.util.tree.TextSprite;
import net.coderodde.util.tree.demo.SimpleStringBinaryTreeNode;

/**
 * This class implements a default binary tree node printer.
 * 
 * @author Rodion "rodde" Efremov 
 * @version 1.6 (Jul 6, 2017)
 * @param <T> the type of the binary tree node values.
 */
public final class DefaultBinaryTreeNodePrinter<T> 
        implements BinaryTreeNodePrinter<T> {

    /**
     * The default top padding.
     */
    private static final int DEFAULT_TOP_PADDING = 0;
    
    /**
     * The default right padding.
     */
    private static final int DEFAULT_RIGHT_PADDING = 0;
    
    /**
     * The default bottom padding.
     */
    private static final int DEFAULT_BOTTOM_PADDING = 0;
    
    /**
     * The default left padding.
     */
    private static final int DEFAULT_LEFT_PADDING = 0;
    
    /**
     * The default character used to print node corners.
     */
    private static final char DEFAULT_CORNER_CHARACTER = '+';
    
    /**
     * The default character used to print horizontal node borders.
     */
    private static final char DEFAULT_HORIZONTAL_BORDER_CHARACTER = '-';
    
    /**
     * The default character used to print vertical node borders.
     */
    private static final char DEFAULT_VERTICAL_BORDER_CHARACTER = '|';
    
    /**
     * The top padding.
     */
    private int paddingTop = DEFAULT_TOP_PADDING;
    
    /**
     * The right padding.
     */
    private int paddingRight = DEFAULT_RIGHT_PADDING;
    
    /**
     * The bottom padding.
     */
    private int paddingBottom = DEFAULT_BOTTOM_PADDING;
    
    /**
     * The left padding.
     */
    private int paddingLeft = DEFAULT_LEFT_PADDING;
    
    /**
     * The character used to represent top left corners.
     */
    private char topLeftCornerCharacter = DEFAULT_CORNER_CHARACTER;
    
    /**
     * The character used to represent top right corners.
     */
    private char topRightCornerCharacter = DEFAULT_CORNER_CHARACTER;
    
    /**
     * The character used to represent bottom left corners.
     */
    private char bottomLeftCornerCharacter = DEFAULT_CORNER_CHARACTER;
    
    /**
     * The character used to represent bottom right corners.
     */
    private char bottomRightCornerCharacter = DEFAULT_CORNER_CHARACTER;
    
    /**
     * The character used to print the top border.
     */
    private char topBorderCharacter = DEFAULT_HORIZONTAL_BORDER_CHARACTER;
    
    /**
     * The character used to print the right border.
     */
    private char rightBorderCharacter = DEFAULT_VERTICAL_BORDER_CHARACTER;
    
    /**
     * The character used to print the bottom border.
     */
    private char bottomBorderCharacter = DEFAULT_HORIZONTAL_BORDER_CHARACTER;
    
    /**
     * The character used to print the left border.
     */
    private char leftBorderCharacter = DEFAULT_VERTICAL_BORDER_CHARACTER;
    
    @Override
    public TextSprite print(BinaryTreeNode<T> node) {
        String value = node.getValue().toString();
        String[] lines = value.split("\n");
        int maximumLineLength = getMaximumLineLength(lines);
        int width = 2 + paddingLeft + paddingRight + maximumLineLength;
        int height = 2 + paddingTop + paddingBottom + lines.length;
        TextSprite textSprite = new TextSprite(width, height);
        printCorners(textSprite);
        printBorders(textSprite);
        printLines(textSprite, lines);
        setEmptyTextSpriteCellsToSpace(textSprite);
        return textSprite;
    }
    
    public int getTopPadding() {
        return paddingTop;
    }
    
    public int getRightPadding() {
        return paddingRight;
    }
    
    public int getBottomPadding() {
        return paddingBottom;
    }
    
    public int getLeftPadding() {
        return paddingLeft;
    }
    
    public char getTopLeftCornerCharacter() {
        return topLeftCornerCharacter;
    }
    
    public char getTopRightCornerCharacter() {
        return topRightCornerCharacter;
    }
    
    public char getBottomLeftCornerCharacter() {
        return bottomLeftCornerCharacter;
    }
    
    public char getBottomRightCornerCharacter() {
        return bottomRightCornerCharacter;
    }
    
    public char getTopBorderCharacter() {
        return topBorderCharacter;
    }
    
    public char getRightBorderCharacter() {
        return rightBorderCharacter;
    }
    
    public char getBottomBorderCharacter() {
        return bottomBorderCharacter;
    }
    
    public char getLeftBorderCharacter() {
        return leftBorderCharacter;
    }
    
    public void setTopPadding(int paddingTop) {
        this.paddingTop = checkPaddingTop(paddingTop);
    }
    
    public void setRightPadding(int paddingRight) {
        this.paddingRight = checkPaddingRight(paddingRight);
    }
    
    public void setBottomPadding(int paddingBottom) {
        this.paddingBottom = checkPaddingBottom(paddingBottom);
    }
    
    public void setLeftPadding(int paddingLeft) {
        this.paddingLeft = checkPaddingLeft(paddingLeft);
    }
    
    public void setTopLeftCornerCharacter(char c) {
        topLeftCornerCharacter = c;
    }
    
    public void setTopRightCornerCharacter(char c) {
        topRightCornerCharacter = c;
    }
    
    public void setBottomLeftCornerCharacter(char c) {
        bottomLeftCornerCharacter = c;
    }
    
    public void setBottomRightCornerCharacter(char c) {
        bottomRightCornerCharacter = c;
    }
    
    public void setTopBorderCharacter(char c) {
        topBorderCharacter = c;
    }
    
    public void setRightBorderCharacter(char c) {
        rightBorderCharacter = c;
    }
    
    public void setBottomBorderCharacter(char c) {
        bottomBorderCharacter = c;
    }
    
    public void setLeftBorderCharacter(char c) {
        leftBorderCharacter = c;
    }
    
    private int checkPadding(int padding, String errorMessage) {
        if (padding < 0) {
            throw new IllegalArgumentException(
                    errorMessage + ": the given padding is negative: " +
                    padding + ". Must be at least 0!");
        }
        
        return padding;
    }
    
    private int checkPaddingTop(int padding) {
        return checkPadding(padding, "Top padding is invalid");
    }
    
    private int checkPaddingRight(int padding) {
        return  checkPadding(padding, "Right padding is invalid");
    }
        
    private int checkPaddingBottom(int padding) {
        return checkPadding(padding, "Bottom padding is invalid");
    }
    
    private int checkPaddingLeft(int padding) {
        return checkPadding(padding, "Left padding is invalid");
    }
    
    private int getMaximumLineLength(String[] lines) {
        int maximumLineLength = 0;
        
        for (String line : lines) {
            maximumLineLength = Math.max(maximumLineLength, line.length());
        }
        
        return maximumLineLength;
    }
    
    private void setEmptyTextSpriteCellsToSpace(TextSprite textSprite) {
        for (int y = 0; y < textSprite.getHeight(); ++y) {
            for (int x = 0; x < textSprite.getWidth(); ++x) {
                char c = textSprite.getChar(x, y);
                
                if (c == '\u0000') {
                    textSprite.setChar(x, y, ' ');
                }
            }
        }
    }
    
    private void printCorners(TextSprite textSprite) {
        int width = textSprite.getWidth();
        int height = textSprite.getHeight();
        textSprite.setChar(0, 0, topLeftCornerCharacter);
        textSprite.setChar(width - 1, 0, topRightCornerCharacter);
        textSprite.setChar(0, height - 1, bottomLeftCornerCharacter);
        textSprite.setChar(width - 1, height - 1, bottomRightCornerCharacter);
    }
    
    private void printBorders(TextSprite textSprite) {
        int width = textSprite.getWidth();
        int height = textSprite.getHeight();
        
        for (int x = 1; x < width - 1; ++x) {
            textSprite.setChar(x, 0, topBorderCharacter);
            textSprite.setChar(x, height - 1, bottomBorderCharacter);
        }
        
        for (int y = 1; y < height - 1; ++y) {
            textSprite.setChar(0, y, leftBorderCharacter);
            textSprite.setChar(width - 1, y, rightBorderCharacter);
        }
    }
    
    private void printLines(TextSprite textSprite, String[] lines) {
        int startY = 1 + paddingTop;
        int startX = 1 + paddingLeft;
        
        for (int y = 0; y < lines.length; ++y) {
            char[] chars = lines[y].toCharArray();
            
            for (int x = 0; x < chars.length; ++x) {
                textSprite.setChar(startX + x, startY + y, chars[x]);
            }
        }
    }
}
