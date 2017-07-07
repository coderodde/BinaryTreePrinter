package net.coderodde.util.tree.support;

import net.coderodde.util.tree.TextSprite;

public final class Utils {

    public static void setEmptyTextSpriteCellsToSpace(TextSprite textSprite) {
        for (int y = 0; y < textSprite.getHeight(); ++y) {
            for (int x = 0; x < textSprite.getWidth(); ++x) {
                char c = textSprite.getChar(x, y);
                
                if (c == '\u0000') {
                    textSprite.setChar(x, y, ' ');
                }
            }
        }
    }
}
