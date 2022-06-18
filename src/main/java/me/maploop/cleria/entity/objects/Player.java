package me.maploop.cleria.entity.objects;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.UI;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.item.Item;
import me.maploop.cleria.object.SuperObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.maploop.cleria.helper.AssetHelper.asset;

public class Player extends Entity
{
    private boolean up, down, left, right;
    public static int keys;

    public Player() {
        super("cleria", (GamePanel.screenWidth / 2) - GamePanel.tileSize / 2, (GamePanel.screenHeight / 2) - GamePanel.tileSize - 2,
                GamePanel.tileSize * 23, GamePanel.tileSize * 21, 4);
        this.hitbox = new Rectangle();
        this.hitbox.x = 8;
        this.hitbox.y = 16;
        this.hitbox.width = 32;
        this.hitbox.height = 32;
        this.hitboxDefaultX = hitbox.x;
        this.hitboxDefaultY = hitbox.y;

        keys = 0;
    }

    @Override
    public void tick() {
        if (up || down || left || right) {

            // Check tile collision
            collisionOn = false;
            GamePanel.collisionChecker.checkTile(this);
            int objectIndex = GamePanel.collisionChecker.checkObject(this, true);
            if (objectIndex != 999)
                touchObject(objectIndex);

            if (!collisionOn) {
                if (up)
                    setWorldY(getWorldY() - getSpeed());
                if (down)
                    setWorldY(getWorldY() + getSpeed());
                if (left)
                    setWorldX(getWorldX() - getSpeed());
                if (right)
                    setWorldX(getWorldX() + getSpeed());
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;

                spriteCounter = 0;
            }
        }
    }

    @Override
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_W) {
            direction = "up";
            up = true;
        }
        if (code == KeyEvent.VK_S) {
            direction = "down";
            down = true;
        }
        if (code == KeyEvent.VK_A) {
            direction = "left";
            left = true;
        }
        if (code == KeyEvent.VK_D) {
            direction = "right";
            right = true;
        }
    }

    @Override
    public void keyReleased(int code) {
        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
    }

    @Override
    public void getImage() {
        up1 = asset("/assets/entity/player/boy_up_1.png");
        up2 = asset("/assets/entity/player/boy_up_2.png");
        down1 = asset("/assets/entity/player/boy_down_1.png");
        down2 = asset("/assets/entity/player/boy_down_2.png");
        left1 = asset("/assets/entity/player/boy_left_1.png");
        left2 = asset("/assets/entity/player/boy_left_2.png");
        right1 = asset("/assets/entity/player/boy_right_1.png");
        right2 = asset("/assets/entity/player/boy_right_2.png");
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                else if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                else if (spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                else if (spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                else if (spriteNum == 2)
                    image = right2;
                break;
        }
        g2d.drawImage(image, getX(), getY(), GamePanel.tileSize, GamePanel.tileSize, null);
    }

    @Override
    public void touchObject(int index) {
        String name = GamePanel.object[index].name;
        switch (name) {
            case "key":
                keys++;
                GamePanel.object[index] = null;
                GamePanel.playSFX("coin");
                GamePanel.chat("You got a key!", Color.yellow);
                break;
            case "door":
                if (keys >= 1) {
                    GamePanel.object[index] = null;
                    keys--;
                    GamePanel.chat("Door unlocked!", Color.green);
                    GamePanel.playSFX("unlock");
                } else {
                    GamePanel.chat("You need a key to open this door!", Color.red);
                }
                break;
            case "boots":
                GamePanel.chat("You found a pair of boots!", Color.cyan);
                speed += 2;
                GamePanel.object[index] = null;
                GamePanel.playSFX("powerup");
                break;
            case "chest":
                UI.gameFinished = true;
                GamePanel.stopMusic();
                GamePanel.playSFX("fanfare");
                break;
        }
    }

    public int getKeys() {
        return keys;
    }
}
