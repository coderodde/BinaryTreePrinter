package net.coderodde.util.tree;

public final class TextSprite {
    
    /**
     * The character used to print corners.
     */
    private static final char CORNER_CHAR = '+';
    
    /**
     * The character used to print horizontal borders.
     */
    private static final char HORIZONTAL_BORDER_CHAR = '-';
    
    /**
     * The character used to print vertical borders.
     */
    private static final char VERTICAL_BORDER_CHAR = '|';
    
    /**
     * The character used to print the arrow tips.
     */
    private static final char ARROW_CHAR = 'V';
    
    /**
     * The minimum width of a sprite in characters.
     */
    private static final int MINIMUM_SPRITE_WIDTH = 1;
    
    /**
     * The minimum height of a sprite in characters.
     */
    private static final int MINIMUM_SPRITE_HEIGHT = 1;
    
    /**
     * The width of this text sprite.
     */
    private final int width;
    
    /**
     * The height of this text sprite.
     */
    private final int height;
    
    /**
     * Contains all the characters.
     */
    private final char[][] window;
    
    /**
     * Constructs a new empty text sprite.
     * 
     * @param spriteWidth  the number of text columns.
     * @param spriteHeight the number of text rows.
     */
    public TextSprite(int spriteWidth, int spriteHeight) {
        this.width = checkWidth(spriteWidth);
        this.height = checkHeight(spriteHeight);
        this.window = new char[spriteHeight][spriteWidth];
    }
    
    /**
     * Sets a particular cell to {@code c}.
     * 
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @param c the character to set.
     */
    public void setChar(int x, int y, char c) {
        checkX(x);
        checkY(y);
        window[y][x] = c;
    }
    
    /**
     * Reads the content of a particular cell.
     * 
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @return the contents of the cell.
     */
    public char getChar(int x, int y) {
        checkX(x);
        checkY(y);
        return window[y][x];
    }
    
    /**
     * Returns the number of columns in this sprite.
     * 
     * @return the number of columns.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Returns the number of rows in this sprite.
     * 
     * @return the number of rows.
     */
    public int getHeight() {
        return height;
    }
    
    private int checkWidth(int width) {
        if (width < MINIMUM_SPRITE_WIDTH) {
            throw new IllegalArgumentException(
                    "The sprite width is too small (" + width + "). " +
                    "Must be at least " + MINIMUM_SPRITE_WIDTH + ".");
        }
        
        return width;
    }
    
    private int checkHeight(int height) {
        if (height < MINIMUM_SPRITE_HEIGHT) {
            throw new IllegalArgumentException(
                    "The sprite height is too small (" + height + "). " +
                    "Must be at least " + MINIMUM_SPRITE_HEIGHT + ".");
        }
        
        return height;
    }
    
    private void checkX(int x) {
        if (x < 0) {
            throw new IndexOutOfBoundsException("x = " + x + " is negative.");
        } else if (x >= width) {
            throw new IndexOutOfBoundsException("x = " + x + " exceeds the " +
                                                "width = " + width);
        }
    }
    
    private void checkY(int y) {
        if (y < 0) {
            throw new IndexOutOfBoundsException("y = " + y + " is negative.");
        } else if (y >= height) {
            throw new IndexOutOfBoundsException("y = " + y + " exceeds the " +
                                                "height = " + height);
        }
    }
}
