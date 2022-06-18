package me.maploop.cleria.object.objects;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;

import java.awt.*;

public class KeyObj extends SuperObject
{
    public KeyObj() {
        name = "key";
        image = AssetHelper.asset("/assets/object/key.png");
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}
