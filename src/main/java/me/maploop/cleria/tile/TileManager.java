package me.maploop.cleria.tile;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.entity.Entity;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TileManager
{
    public Map<Integer, Tile> tile;
    public int mapTileIndex[][];

    public TileManager() {
        tile = new HashMap<>();
        mapTileIndex = new int[GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        getTileImage();
        load();
    }

    public void getTileImage() {
        tile.put(0, new Tile(AssetHelper.asset("/assets/tiles/grass.png")));
        tile.put(1, new Tile(AssetHelper.asset("/assets/tiles/wall.png")).collision(true));
        tile.put(2, new Tile(AssetHelper.asset("/assets/tiles/water.png")));
        tile.put(3, new Tile(AssetHelper.asset("/assets/tiles/earth.png")));
        tile.put(4, new Tile(AssetHelper.asset("/assets/tiles/tree.png")).collision(true));
        tile.put(5, new Tile(AssetHelper.asset("/assets/tiles/sand.png")));
    }

    public void draw(Graphics2D g2d) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.maxWorldCol && worldRow < GamePanel.maxWorldRow) {
            int tileNum = mapTileIndex[worldCol][worldRow];
            int worldX = worldCol * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = worldX - Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX();
            int screenY = worldY - Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY();

            if (worldX + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldX() - Entity.getByName("cleria").getX() &&
            worldX - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldX() + Entity.getByName("cleria").getX() &&
            worldY + (GamePanel.tileSize * 2) > Entity.getByName("cleria").getWorldY() - Entity.getByName("cleria").getY() &&
            worldY - (GamePanel.tileSize * 2) < Entity.getByName("cleria").getWorldY() + Entity.getByName("cleria").getY()) {
                g2d.drawImage(tile.get(tileNum).image, screenX, screenY, GamePanel.tileSize, GamePanel.tileSize, null);
            }
            worldCol++;

            if (worldCol == GamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void load() {
        try {
            InputStream is = getClass().getResourceAsStream("/assets/map/" + GamePanel.levelName + ".cmapmeta");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < GamePanel.maxWorldCol && row < GamePanel.maxWorldRow) {
                String line = reader.readLine();
                while (col < GamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileIndex[col][row] = num;
                    col++;
                }
                if (col == GamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
