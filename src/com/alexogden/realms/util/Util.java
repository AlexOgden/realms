package com.alexogden.realms.util;

import java.awt.Graphics;

public final class Util {
	
    public static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}
