package me.maploop.cleria.object;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2d) {
        int screenX = worldX - Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX();
        int screenY = worldY - Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY();

        if (worldX + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldX() - Entity.getByName("cleria").getX() &&
                worldX - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX() &&
                worldY + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldY() - Entity.getByName("cleria").getY() &&
                worldY - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY()) {
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }
}
