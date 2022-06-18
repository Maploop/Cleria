package me.maploop.cleria.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity
{
    public static final Map<String, Entity> gameObjectRegistry = new HashMap<>();

    private final String name;
    private int x, y;
    private int worldX, worldY;
    private int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter;
    public int spriteNum;
    public Rectangle hitbox;
    public boolean collisionOn = false;

    public Entity(String name, int x, int y, int worldX, int worldY, int speed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.worldX = worldX;
        this.worldY = worldY;
        this.speed = speed;
        getImage();
        direction = "down";
        spriteCounter = 0;
        spriteNum = 1;
        gameObjectRegistry.put(name, this);
    }

    public abstract void draw(Graphics2D g2d);

    public void keyPressed(int code) { }
    public void keyReleased(int code) { }
    public void tick() { }
    public void getImage() { }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Entity getByName(String n) {
        return gameObjectRegistry.get(n);
    }
}
