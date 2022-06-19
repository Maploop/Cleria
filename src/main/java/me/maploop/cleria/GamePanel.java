package me.maploop.cleria;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.helper.ChatComponent;
import me.maploop.cleria.key.KeyHandler;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.object.SuperObject;
import me.maploop.cleria.tile.TileManager;
import me.maploop.cleria.ui.DialogueUI;
import me.maploop.cleria.ui.GameUI;
import me.maploop.cleria.ui.PausedUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GamePanel extends JPanel implements Runnable
{
    public static GamePanel INSTANCE;

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
    public static final String levelName = "worldV2";
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;

    public int fps = 0;

    public static KeyHandler keyHandler = new KeyHandler();
    public static Sound sound = new Sound();
    public static Sound se = new Sound();
    public static Thread gameThread;
    public static CollisionChecker collisionChecker = new CollisionChecker();
    public static TileManager tileManager = new TileManager();
    public static SuperObject object[] = new SuperObject[10];
    public static UI ui = new UI();
    public static int gameState;

    public static boolean chatOpen = false;
    public static String chatMessage = "";

    public static final int PLAYING = 1;
    public static final int PAUSED = 2;
    public static final int DIALOGUE = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        INSTANCE = this;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGameObjects() {
        AssetHelper.setObject();
        playMusic("cleria");

        new PausedUI();
        new DialogueUI();

        gameState = PLAYING;
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

    private static Map<ChatComponent, Long> chatmessages = new HashMap<>();
    public static void chat(String msg) {
        chatmessages.put(new ChatComponent(msg, Color.white), System.currentTimeMillis() + 3000);
    }

    public static void chat(String msg, Color c) {
        chatmessages.put(new ChatComponent(msg, c), System.currentTimeMillis() + 3000);
    }

    public void update() {
        if (gameState == PLAYING) {
            Entity.gameObjectRegistry.values().forEach(Entity::tick);
        }
        if (gameState == PAUSED) {
            // Do nothing
        }
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
        if (chatOpen) {
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillRect(0, getHeight() - 20, getWidth(), 20);
            g2d.setColor(Color.white);
            g2d.drawString("> " + chatMessage, 10, getHeight() - 5);
        }
        if (!chatmessages.isEmpty()) {
            int x = 10;
            AtomicInteger y = new AtomicInteger(80);
            Map<ChatComponent, Long> cached = new HashMap<>(chatmessages);
            cached.forEach((k, v) -> {
                if (v <= System.currentTimeMillis()) {
                    chatmessages.remove(k);
                    return;
                }

                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(x, y.get(), 300, 20);
                g2d.setColor(k.color);
                g2d.drawString(k.chat, x + 10, y.get() + 15);
                y.addAndGet(20);
            });
        }
        ui.draw(g2d);
    }

    public static void playMusic(String i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public static void playSFX(String i) {
        se.setFile(i);
        se.play();
    }

    public static void stopMusic() {
        sound.stop();
    }
}
