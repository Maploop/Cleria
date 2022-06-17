package me.maploop.cleria.tile;

import java.awt.image.BufferedImage;

public class Tile
{
    public BufferedImage image;
    public boolean collision = false;

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public Tile collision(boolean flag) {
        this.collision = flag;
        return this;
    }
}
