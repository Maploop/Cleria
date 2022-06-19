package me.maploop.cleria.ui;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.GeneralHelper;

import java.awt.*;

public class DialogueUI extends GameUI
{
    public static String currentDialogue;

    @Override
    public int stateId() {
        return 3;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = GamePanel.tileSize * 2, y = GamePanel.tileSize / 2, width = GamePanel.screenWidth - (GamePanel.tileSize * 4), height = GamePanel.tileSize * 4;
        GeneralHelper.drawSubWindow(g2d, x, y, width, height, Color.black);

        x += GamePanel.tileSize;
        y += GamePanel.tileSize;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20));

        for (String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += g2d.getFontMetrics().getHeight();
        }
    }
}
