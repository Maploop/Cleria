package me.maploop.cleria.ui;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.GeneralHelper;

import java.awt.*;

public class NoticeUI extends GameUI
{
    public static String noticeMessage;
    public static Color noticeColor = Color.red;

    @Override
    public int stateId() {
        return 999;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = GamePanel.tileSize * 2, y = GamePanel.tileSize / 2, width = GamePanel.screenWidth - (GamePanel.tileSize * 4), height = GamePanel.tileSize * 4;
        GeneralHelper.drawSubWindow(g2d, x, y, width, height, Color.black);
        x = GeneralHelper.getCenteredTextX(noticeMessage, g2d);
        y += GamePanel.tileSize;
        g2d.setColor(noticeColor);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 20));
        g2d.drawString("NOTICE", x, y);

        x = GamePanel.tileSize * 2;
        y = GamePanel.tileSize / 2;

        x += GamePanel.tileSize;
        y += GamePanel.tileSize * 2;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20));
        g2d.setColor(Color.white);

        for (String line : noticeMessage.split("\n")) {
            g2d.drawString(line, x, y);
            y += g2d.getFontMetrics().getHeight();
        }
    }
}
