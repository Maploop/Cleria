package me.maploop.cleria.key;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e) {
        if (GamePanel.chatOpen) {
            GamePanel.chatMessage += e.getKeyChar();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (!GamePanel.chatOpen)
            Entity.gameObjectRegistry.values().forEach(v -> v.keyPressed(code));

        if (e.getKeyCode() == 8) {
            if (GamePanel.chatOpen) {
                StringBuilder buffer = new StringBuilder(GamePanel.chatMessage);
                try {
                    buffer.deleteCharAt(buffer.length() - 1);
                } catch (Exception ignored) { }
                GamePanel.chatMessage = buffer.toString();
                return;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (GamePanel.chatOpen) {
                if (GamePanel.chatMessage != "") {
                    GamePanel.chat("<Player> " + GamePanel.chatMessage);
                    GamePanel.chatMessage = "";
                }
                GamePanel.chatOpen = false;
            } else {
                GamePanel.chatOpen = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (GamePanel.chatOpen) {
                GamePanel.chatOpen = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        Entity.gameObjectRegistry.values().forEach(v -> v.keyReleased(code));
    }
}
