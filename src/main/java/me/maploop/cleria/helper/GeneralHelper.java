package me.maploop.cleria.helper;

import me.maploop.cleria.GamePanel;

public class GeneralHelper
{
    public static int getWorldPos(int x) {
        return x * GamePanel.tileSize;
    }
}
