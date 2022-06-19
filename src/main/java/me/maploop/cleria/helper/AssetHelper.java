package me.maploop.cleria.helper;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.object.objects.BootsObj;
import me.maploop.cleria.object.objects.ChestObj;
import me.maploop.cleria.object.objects.DoorObj;
import me.maploop.cleria.object.objects.KeyObj;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

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

    public static URL assetUrl(String p) {
        return AssetHelper.class.getResource(p);
    }

    public static void setObject() {
        Entity.getByName("oldman").worldX = GeneralHelper.getWorldPos(21);
        Entity.getByName("oldman").worldY = GeneralHelper.getWorldPos(21);
    }
}
