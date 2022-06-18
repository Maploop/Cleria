package me.maploop.cleria.helper;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.object.objects.ChestObj;
import me.maploop.cleria.object.objects.DoorObj;
import me.maploop.cleria.object.objects.KeyObj;

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

    public static void setObject() {
        GamePanel.object[0] = new KeyObj();
        GamePanel.object[0].worldX = GeneralHelper.getWorldPos(23);
        GamePanel.object[0].worldY = GeneralHelper.getWorldPos(7);

        GamePanel.object[1] = new KeyObj();
        GamePanel.object[1].worldX = GeneralHelper.getWorldPos(23);
        GamePanel.object[1].worldY = GeneralHelper.getWorldPos(40);

        GamePanel.object[2] = new DoorObj();
        GamePanel.object[2].worldX = GeneralHelper.getWorldPos(10);
        GamePanel.object[2].worldY = GeneralHelper.getWorldPos(11);

        GamePanel.object[3] = new DoorObj();
        GamePanel.object[3].worldX = GeneralHelper.getWorldPos(8);
        GamePanel.object[3].worldY = GeneralHelper.getWorldPos(28);

        GamePanel.object[4] = new DoorObj();
        GamePanel.object[4].worldX = GeneralHelper.getWorldPos(12);
        GamePanel.object[4].worldY = GeneralHelper.getWorldPos(22);

        GamePanel.object[5] = new DoorObj();
        GamePanel.object[5].worldX = GeneralHelper.getWorldPos(37);
        GamePanel.object[5].worldY = GeneralHelper.getWorldPos(7);

        GamePanel.object[6] = new ChestObj();
        GamePanel.object[6].worldX = GeneralHelper.getWorldPos(10);
        GamePanel.object[6].worldY = GeneralHelper.getWorldPos(7);
    }
}
