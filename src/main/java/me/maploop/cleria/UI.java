package me.maploop.cleria;

import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;
import me.maploop.cleria.object.objects.HeartObj;
import me.maploop.cleria.ui.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI
{
    Font arial40, arial80;
    Graphics2D g2d;

    public static boolean messageOn = false;
    public static String message = "";
    public static boolean gameFinished = false;


    public SuperObject heart;

    public static  void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }
    public UI() {
        try {
            InputStream is = getClass().getResourceAsStream("/assets/font/main_font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(Font.BOLD, 40);

            arial40 = font;
            arial80 = font.deriveFont(Font.BOLD, 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
        heart = new HeartObj();

    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial40);
        g2d.setColor(Color.white);

        if (GamePanel.gameState == GameState.PLAYING) {
            // HUD

            drawPlayerLife();
        } else {
            GameUI ui = GameUI.registry.get(GamePanel.gameState);
            if (ui == null) return;
            if (ui.blackScreen()) {
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, GamePanel.screenWidth, GamePanel.screenHeight);
                g2d.setFont(arial40);
                g2d.setColor(Color.white);
            }
            ui.draw(g2d);
        }
    }

    public void drawPlayerLife() {
        int x = GamePanel.tileSize / 2;
        int y = GamePanel.tileSize / 2;
        int i = 0;

        while (i < Entity.getByName("cleria").statistic_maxHealth / 2) {
            g2d.drawImage(heart.image2, x, y, null);
            i++;
            x += GamePanel.tileSize + (GamePanel.tileSize / 3);
        }

        x = GamePanel.tileSize / 2;
        y = GamePanel.tileSize / 2;
        i = 0;
        while (i < GamePanel.player.statistic_health) {
            g2d.drawImage(heart.image1, x, y, null);
            i++;
            if (i < GamePanel.player.statistic_health) {
                g2d.drawImage(heart.image, x, y, null);
            }
            i++;
            x += GamePanel.tileSize + (GamePanel.tileSize / 3);
        }
    }
}
