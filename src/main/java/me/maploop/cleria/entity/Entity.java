package me.maploop.cleria.entity;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.ai.AIGoal;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;
import me.maploop.cleria.ui.DialogueUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public abstract class Entity
{
    public static final Map<String, Entity> gameObjectRegistry = new HashMap<>();

    public final String name;
    public int x, y;
    public int worldX, worldY;
    public int speed;
    public int hitboxDefaultX, hitboxDefaultY;
    public List<AIGoal> goalSelector;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter;
    public int spriteNum;
    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    public boolean npc;
    public List<String> dialogues;
    public int dialogueIndex;

    // Statistics
    public int statistic_maxHealth;
    public int statistic_health;

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
        npc = false;
        this.goalSelector = new ArrayList<>();
        this.dialogues = new ArrayList<>();
        this.dialogueIndex = 0;

        initAI();
        initDialogue();
        initStatistics();
        statistic_health = statistic_maxHealth;
    }

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

        int screenX = worldX - Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX();
        int screenY = worldY - Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY();

        if (worldX + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldX() - Entity.getByName("cleria").getX() &&
                worldX - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX() &&
                worldY + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldY() - Entity.getByName("cleria").getY() &&
                worldY - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY()) {
            g2d.drawImage(image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
        }
    }

    public void keyPressed(int code) { }
    public void keyReleased(int code) { }
    public void getImage() { }
    public void touchObject(int index) { }
    public void touchEntity(String name) { }
    public void initAI() { }
    public void initDialogue() { }
    public void initStatistics() { }

    public void tick() {
        for (AIGoal goal : goalSelector) {
            goal.update(this);
        }

        collisionOn = false;
        GamePanel.collisionChecker.checkTile(this);
        GamePanel.collisionChecker.checkObject(this, false);
        GamePanel.collisionChecker.checkPlayer(this);

        if (!collisionOn) {
            if (direction.equals("up"))
                setWorldY(getWorldY() - getSpeed());
            if (direction.equals("down"))
                setWorldY(getWorldY() + getSpeed());
            if (direction.equals("left"))
                setWorldX(getWorldX() - getSpeed());
            if (direction.equals("right"))
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

    public void speak() {
        DialogueUI.currentDialogue = dialogues.get(dialogueIndex);
        if (dialogueIndex != dialogues.size() - 1)
            dialogueIndex++;
        else {
//            Player.dontApplyDialogue = true;
            dialogueIndex = 0;
            DialogueUI.currentDialogue = dialogues.get(dialogueIndex);
        }

        Player player = (Player) Entity.getByName("cleria");
        switch (player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public static Entity getByName(String n) {
        return gameObjectRegistry.get(n);
    }

    public BufferedImage defaultAsset(String name) {
        return AssetHelper.asset("/assets/entity" + name + ".png");
    }
}
