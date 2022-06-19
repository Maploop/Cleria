package me.maploop.cleria.ui;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.GeneralHelper;

import java.awt.*;

public class PausedUI extends GameUI
{
    @Override
    public int stateId() {
        return 2;
    }

    @Override
    public boolean blackScreen() {
        return true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String text = "PAUSED";
        int x = GeneralHelper.getCenteredTextX(text, g2d), y = GamePanel.screenHeight / 5;

        g2d.drawString(text, x, y);

        UIButton button = new UIButton("Exit", (GamePanel.screenWidth / 2) - 50, GamePanel.screenHeight / 2, 100, 50);
        button.draw(GamePanel.INSTANCE, g2d);
    }
}
