package me.maploop.cleria.object.objects;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;

public class ChestObj extends SuperObject
{
    public ChestObj() {
        name = "chest";
        image = AssetHelper.asset("/assets/object/chest.png");
    }
}
