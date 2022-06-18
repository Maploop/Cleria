package me.maploop.cleria;

import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI
{
    Font arial40, arial80;
    BufferedImage keyImage;
    public static boolean messageOn = false;
    public static String message = "";
    public static boolean gameFinished = false;

    public static  void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }
    public UI() {
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        keyImage = AssetHelper.asset("/assets/object/key.png");
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(arial40);
        g2d.setColor(Color.white);
        g2d.drawImage(keyImage, GamePanel.tileSize / 2, GamePanel.tileSize / 2, GamePanel.tileSize, GamePanel.tileSize, null);
        g2d.drawString("x" + Player.keys, 74, 65);

        if (gameFinished) {
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillRect(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);

            g2d.setFont(arial40);
            g2d.setColor(Color.white);

            String text = "You found the treasure!";
            int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            int x, y;

            x = GamePanel.screenWidth / 2 - (textLength / 2);
            y = GamePanel.screenHeight / 2 - (GamePanel.tileSize * 3);
            g2d.drawString(text, x, y);

            g2d.setFont(arial80);
            g2d.setColor(Color.cyan);

            text = "Congratulations!";
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = GamePanel.screenWidth / 2 - (textLength / 2);
            y = GamePanel.screenHeight / 2 - (GamePanel.tileSize);
            g2d.drawString(text, x, y);

            GamePanel.gameThread = null;
        }
    }
}
