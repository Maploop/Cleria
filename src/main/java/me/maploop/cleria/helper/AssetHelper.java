package me.maploop.cleria.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class AssetHelper
{
    public static BufferedImage asset(String path) {
        try {
            return ImageIO.read(AssetHelper.class.getResourceAsStream(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
