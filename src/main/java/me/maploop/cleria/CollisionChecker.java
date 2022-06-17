package me.maploop.cleria;

import me.maploop.cleria.object.Entity;

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
}
