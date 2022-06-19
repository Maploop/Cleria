package me.maploop.cleria.object;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject
{
    public BufferedImage image;
    public BufferedImage image1;
    public BufferedImage image2;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public int hitboxDefaultX = 0, hitboxDefaultY = 0;
    public String tooltips;

    public boolean mouseIn;

    public boolean displayTooltips;
    public int tooltipsX, tooltipsY;

    public void interact() { }


    public void drag(int mouseX, int mouseY) {
        tooltipsX = mouseX;
        tooltipsY = mouseY;
    }

    public void mouseEnter() {
        System.out.println("entered " + name);
        displayTooltips = true;
        mouseIn = true;
    }

    public void mouseExit() {
        System.out.println("exited " + name);
        displayTooltips = false;
        mouseIn = false;
    }

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
