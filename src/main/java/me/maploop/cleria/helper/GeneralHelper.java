package me.maploop.cleria.helper;

import me.maploop.cleria.GamePanel;

import java.awt.*;

public class GeneralHelper
{
    public static int getWorldPos(int x) {
        return x * GamePanel.tileSize;
    }

    public static int getCenteredTextX(String text, Graphics2D g2d) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return GamePanel.screenWidth / 2 - length / 2;
    }

    public static void drawSubWindow(Graphics2D g2d, int x, int y, int width, int height, Color color) {
        g2d.setColor(new Color(0, 0, 0, 210));
        g2d.fillRoundRect(x, y, width, height, 35, 35);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 35, 35);
    }
}
