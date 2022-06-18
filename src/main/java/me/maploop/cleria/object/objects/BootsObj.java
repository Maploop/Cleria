package me.maploop.cleria.object.objects;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;

public class BootsObj extends SuperObject
{
    public BootsObj() {
        name = "boots";
        image = AssetHelper.asset("/assets/object/boots.png");
        collision = true;
    }
}
