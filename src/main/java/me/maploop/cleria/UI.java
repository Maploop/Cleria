package me.maploop.cleria;

import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.ui.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI
{
    Font arial40, arial80;
    Graphics2D g2d;

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
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial40);
        g2d.setColor(Color.white);

        if (GamePanel.gameState == GamePanel.PLAYING) {
            // playing
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
}
