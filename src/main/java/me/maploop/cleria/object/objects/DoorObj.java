package me.maploop.cleria.object.objects;

import me.maploop.cleria.helper.AssetHelper;
import me.maploop.cleria.object.SuperObject;

public class DoorObj extends SuperObject
{
    public DoorObj() {
        name = "door";
        image = AssetHelper.asset("/assets/object/door_close.png");
        collision = true;
    }
}
