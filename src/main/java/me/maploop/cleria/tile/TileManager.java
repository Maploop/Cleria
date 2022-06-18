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
        tile.put(0, new Tile(AssetHelper.asset("/assets/tiles/grass00.png")));
        tile.put(1, new Tile(AssetHelper.asset("/assets/tiles/wall.png")).collision(true));
        tile.put(2, new Tile(AssetHelper.asset("/assets/tiles/water.png")));
        tile.put(3, new Tile(AssetHelper.asset("/assets/tiles/earth.png")));
        tile.put(4, new Tile(AssetHelper.asset("/assets/tiles/tree.png")).collision(true));
        tile.put(6, new Tile(AssetHelper.asset("/assets/tiles/sand.png")));
        tile.put(7, new Tile(AssetHelper.asset("/assets/tiles/sand.png")));
        tile.put(8, new Tile(AssetHelper.asset("/assets/tiles/sand.png")));
        tile.put(9, new Tile(AssetHelper.asset("/assets/tiles/sand.png")));

        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
    }

    public void setup(int i, String s, boolean collision) {
        tile.put(i, new Tile(AssetHelper.asset("/assets/tiles/" + s + ".png")).collision(collision));
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
