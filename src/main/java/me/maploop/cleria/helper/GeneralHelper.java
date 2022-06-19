package me.maploop.cleria.helper;

import me.maploop.cleria.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneralHelper
{
    public static int getWorldPos(int x) {
        return x * GamePanel.tileSize;
    }

    public static int getCenteredTextX(String text, Graphics2D g2d) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return GamePanel.screenWidth / 2 - length / 2;
    }

    public static int getCenteredTextX(String text, Graphics2D g2d, int customWidth) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return customWidth / 2 - length / 2;
    }

    public static void drawSubWindow(Graphics2D g2d, int x, int y, int width, int height, Color color) {
        g2d.setColor(new Color(0, 0, 0, 210));
        g2d.fillRoundRect(x, y, width, height, 35, 35);
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 35, 35);
    }

    public static BufferedImage getScaledImage(BufferedImage imageToScale, int dWidth, int dHeight) {
        Image tmp = imageToScale.getScaledInstance(dWidth, dHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
