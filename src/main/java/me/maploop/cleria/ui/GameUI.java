package me.maploop.cleria.ui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class GameUI
{
    public static final Map<Integer, GameUI> registry = new HashMap<>();

    public abstract int stateId();
    public abstract void draw(Graphics2D g2d);
    public boolean blackScreen() { return false; }

    public GameUI() {
        registry.put(stateId(), this);
    }
}
