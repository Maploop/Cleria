package me.maploop.cleria.object.objects;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.helper.GeneralHelper;
import me.maploop.cleria.object.SuperObject;

public class HeartObj extends SuperObject
{
    public HeartObj() {
        name = "heart";
        image = GeneralHelper.getScaledImage(AssetHelper.asset("/assets/ui/heart_full.png"), GamePanel.tileSize, GamePanel.tileSize);
        image1 = GeneralHelper.getScaledImage(AssetHelper.asset("/assets/ui/heart_half.png"), GamePanel.tileSize, GamePanel.tileSize);
        image2 = GeneralHelper.getScaledImage(AssetHelper.asset("/assets/ui/heart_empty.png"), GamePanel.tileSize, GamePanel.tileSize);

        collision = true;
    }
}
