package me.maploop.cleria;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.key.KeyHandler;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.object.SuperObject;
import me.maploop.cleria.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // Settings
    public static final int originalTileSize = 16;
    public static final int scale = 3;
    public static final int maxFps = 60;
    public static final boolean drawFps = true;

    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;
    // Settings end

    // World Settings
    public static final String levelName = "world01";
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;

    public int fps = 0;

    public static KeyHandler keyHandler = new KeyHandler();
    public static Thread gameThread;
    public static CollisionChecker collisionChecker = new CollisionChecker();
    public static TileManager tileManager = new TileManager();
    public static SuperObject object[] = new SuperObject[10];
    //

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGameObjects() {
        AssetHelper.setObject();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / maxFps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                fps = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        Entity.gameObjectRegistry.values().forEach(Entity::tick);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        Entity.gameObjectRegistry.values().forEach(e -> e.draw(g2d));
        for (SuperObject objects : object) {
            if (objects == null) continue;
            objects.draw(g2d);
        }

        if (drawFps) {
            g2d.setColor(Color.white);
            g2d.drawString("FPS: " + fps, 10, 20);
        }
    }
}
