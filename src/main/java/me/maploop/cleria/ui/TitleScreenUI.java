package me.maploop.cleria.ui;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.helper.GeneralHelper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleScreenUI extends GameUI {
    public static String currentDialogue;
    public static int commandNum = 0;

    @Override
    public int stateId() {
        return 0;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80));
        BufferedImage bg = AssetHelper.asset("/assets/icon/title_screen_bg.png");
        String text = "Cleria";
        g2d.setColor(new Color(144, 66, 245));
        assert bg != null;
//        g2d.drawImage(GeneralHelper.getScaledImage(bg, GamePanel.screenWidth, GamePanel.screenHeight), 0, 0, null);
        int x = GeneralHelper.getCenteredTextX(text, g2d), y = GamePanel.tileSize * 2;
        // Shadow
        g2d.setColor(Color.gray);
        g2d.drawString(text, x + 5, y + 5);
        // Color
        g2d.setColor(Color.red);
        g2d.drawString(text, x, y);

        // main Char image
        x = GamePanel.screenWidth / 2 - (GamePanel.tileSize * 2) / 2;
        y += GamePanel.tileSize;
        g2d.drawImage(GeneralHelper.getScaledImage(AssetHelper.asset("/assets/entity/player/boy_down_1.png"), 100, 100), x, y, null);

        // menu
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = GeneralHelper.getCenteredTextX(text, g2d);
        y += GamePanel.tileSize * 4;
        g2d.drawString(text, x, y);
        if (commandNum == 0) {
            g2d.setColor(Color.green);
            g2d.drawString("> ", x - GamePanel.tileSize, y);
            g2d.setColor(Color.white);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Continue";
        x = GeneralHelper.getCenteredTextX(text, g2d);
        y += GamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNum == 1) {
            g2d.setColor(Color.green);
            g2d.drawString("> ", x - GamePanel.tileSize, y);
            g2d.setColor(Color.white);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "Quit";
        x = GeneralHelper.getCenteredTextX(text, g2d);
        y += GamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNum == 2) {
            g2d.setColor(Color.green);
            g2d.drawString("> ", x - GamePanel.tileSize, y);
            g2d.setColor(Color.white);
        }
    }
}
