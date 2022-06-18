package me.maploop.cleria.key;

import me.maploop.cleria.entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        Entity.gameObjectRegistry.values().forEach(v -> v.keyPressed(code));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        Entity.gameObjectRegistry.values().forEach(v -> v.keyReleased(code));
    }
}
