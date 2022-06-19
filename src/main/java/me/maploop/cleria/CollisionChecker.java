package me.maploop.cleria;

import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.object.SuperObject;

import java.awt.*;

public class CollisionChecker
{
    public CollisionChecker() {

    }

    public void checkTile(Entity e) {
        int entityLeftWorldX = e.getWorldX() + e.hitbox.x;
        int entityRightWorldX = e.getWorldX() + e.hitbox.x + e.hitbox.width;
        int entityTopWorldY = e.getWorldY() + e.hitbox.y;
        int entityBottomWorldY = e.getWorldY() + e.hitbox.y + e.hitbox.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.tileSize;
        int entityRightCol = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - e.getSpeed()) / GamePanel.tileSize;
                tileNum1 = GamePanel.tileManager.mapTileIndex[entityLeftCol][entityTopRow];
                tileNum2 = GamePanel.tileManager.mapTileIndex[entityRightCol][entityTopRow];
                if (GamePanel.tileManager.tile.get(tileNum1).collision || GamePanel.tileManager.tile.get(tileNum2).collision) {
                    e.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + e.getSpeed()) / GamePanel.tileSize;
                tileNum1 = GamePanel.tileManager.mapTileIndex[entityLeftCol][entityBottomRow];
                tileNum2 = GamePanel.tileManager.mapTileIndex[entityRightCol][entityBottomRow];
                if (GamePanel.tileManager.tile.get(tileNum1).collision || GamePanel.tileManager.tile.get(tileNum2).collision) {
                    e.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - e.getSpeed()) / GamePanel.tileSize;
                tileNum1 = GamePanel.tileManager.mapTileIndex[entityLeftCol][entityTopRow];
                tileNum2 = GamePanel.tileManager.mapTileIndex[entityLeftCol][entityBottomRow];
                if (GamePanel.tileManager.tile.get(tileNum1).collision || GamePanel.tileManager.tile.get(tileNum2).collision) {
                    e.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + e.getSpeed()) / GamePanel.tileSize;
                tileNum1 = GamePanel.tileManager.mapTileIndex[entityRightCol][entityTopRow];
                tileNum2 = GamePanel.tileManager.mapTileIndex[entityRightCol][entityBottomRow];
                if (GamePanel.tileManager.tile.get(tileNum1).collision || GamePanel.tileManager.tile.get(tileNum2).collision) {
                    e.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity e, boolean player) {
        int index = 999;

        for (int i = 0; i < GamePanel.object.length; i++) {
            if (GamePanel.object[i] == null) continue;
            e.hitbox.x = e.worldX + e.hitbox.x;
            e.hitbox.y = e.worldY + e.hitbox.y;
            SuperObject object = GamePanel.object[i];
            object.hitbox.x = object.worldX + object.hitbox.x;
            object.hitbox.y = object.worldY + object.hitbox.y;

            switch (e.direction) {
                case "up":
                    e.hitbox.y -= e.speed;
                    if (e.hitbox.intersects(object.hitbox) && object.collision) {
                        e.collisionOn = true;
                        if (player)
                            index = i;
                    }
                    break;
                case "down":
                    e.hitbox.y += e.speed;
                    if (e.hitbox.intersects(object.hitbox) && object.collision) {
                        e.collisionOn = true;
                        if (player)
                            index = i;
                    }
                    break;
                case "left":
                    e.hitbox.x -= e.speed;
                    if (e.hitbox.intersects(object.hitbox) && object.collision) {
                        e.collisionOn = true;
                        if (player)
                            index = i;
                    }
                    break;
                case "right":
                    e.hitbox.x += e.speed;
                    if (e.hitbox.intersects(object.hitbox) && object.collision) {
                        e.collisionOn = true;
                        if (player)
                            index = i;
                    }
                    break;
            }

            e.hitbox.x = e.hitboxDefaultX;
            e.hitbox.y = e.hitboxDefaultY;
            object.hitbox.x = object.hitboxDefaultX;
            object.hitbox.y = object.hitboxDefaultY;
        }

        return index;
    }

    public String checkEntity(Entity e, Entity[] target) {
        String index = "none";

        for (int i = 0; i < target.length; i++) {
            if (target[i] == null) continue;
            e.hitbox.x = e.worldX + e.hitbox.x;
            e.hitbox.y = e.worldY + e.hitbox.y;
            Entity object = target[i];
            object.hitbox.x = object.worldX + object.hitbox.x;
            object.hitbox.y = object.worldY + object.hitbox.y;

            switch (e.direction) {
                case "up":
                    e.hitbox.y -= e.speed;
                    if (e.hitbox.intersects(object.hitbox)) {
                        e.collisionOn = true;
                        index = target[i].name;
                    }
                    break;
                case "down":
                    e.hitbox.y += e.speed;
                    if (e.hitbox.intersects(object.hitbox)) {
                        e.collisionOn = true;
                        index = target[i].name;
                    }
                    break;
                case "left":
                    e.hitbox.x -= e.speed;
                    if (e.hitbox.intersects(object.hitbox)) {
                        e.collisionOn = true;
                        index = target[i].name;
                    }
                    break;
                case "right":
                    e.hitbox.x += e.speed;
                    if (e.hitbox.intersects(object.hitbox)) {
                        e.collisionOn = true;
                        index = target[i].name;
                    }
                    break;
            }

            e.hitbox.x = e.hitboxDefaultX;
            e.hitbox.y = e.hitboxDefaultY;
            object.hitbox.x = object.hitboxDefaultX;
            object.hitbox.y = object.hitboxDefaultY;
        }

        return index;
    }

    public boolean checkPlayer(Entity e) {
        boolean c = false;
        e.hitbox.x = e.worldX + e.hitbox.x;
        e.hitbox.y = e.worldY + e.hitbox.y;
        Entity object = Entity.getByName("cleria");
        object.hitbox.x = object.worldX + object.hitbox.x;
        object.hitbox.y = object.worldY + object.hitbox.y;

        switch (e.direction) {
            case "up":
                e.hitbox.y -= e.speed;
                break;
            case "down":
                e.hitbox.y += e.speed;
                break;
            case "left":
                e.hitbox.x -= e.speed;
                break;
            case "right":
                e.hitbox.x += e.speed;
                break;
        }

        if (e.hitbox.intersects(object.hitbox)) {
            e.collisionOn = true;
            c = true;
        }

        e.hitbox.x = e.hitboxDefaultX;
        e.hitbox.y = e.hitboxDefaultY;
        object.hitbox.x = object.hitboxDefaultX;
        object.hitbox.y = object.hitboxDefaultY;

        return c;
    }

    public void clickMouse(int x, int y) {
        for (SuperObject o : GamePanel.object) {
            if (o == null) continue;
            if (o.hitbox.contains(x, y)) {
                System.out.println("interacting with " + o.name);
                o.interact();
            }
        }
    }

    public void dragMouse(int x, int y) {
        for (SuperObject o : GamePanel.object) {
            if (o == null) continue;
            if (o.hitbox.contains(x, y)) {
                o.drag(x, y);
                if (!o.mouseIn)
                    o.mouseEnter();
            } else {
                if (o.mouseIn)
                    o.mouseExit();
            }
        }
    }

    public void enterMouse(int x, int y) {
        for (SuperObject o : GamePanel.object) {
            if (o.hitbox.intersects(new Rectangle(x, y, 10, 10))) {
                o.mouseEnter();
            }
        }
    }

    public void exitMouse(int x, int y) {
        for (SuperObject o : GamePanel.object) {
            if (o.hitbox.intersects(new Rectangle(x, y, 10, 10))) {
                o.mouseExit();
            }
        }
    }
}
