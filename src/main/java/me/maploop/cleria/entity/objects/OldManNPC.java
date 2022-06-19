package me.maploop.cleria.entity.objects;

import me.maploop.cleria.ai.AIGoalRandomStroll;
import me.maploop.cleria.entity.Entity;

import java.awt.*;
import java.util.Random;

import static me.maploop.cleria.helper.AssetHelper.asset;

public class OldManNPC extends Entity
{
    public OldManNPC() {
        super("oldman", 0, 0, 0, 0, 1);
        direction = "down";
        npc = true;

        this.hitbox = new Rectangle();
        this.hitbox.x = 8;
        this.hitbox.y = 16;
        this.hitbox.width = 32;
        this.hitbox.height = 32;
        this.hitboxDefaultX = hitbox.x;
        this.hitboxDefaultY = hitbox.y;
    }

    @Override
    public void getImage() {
        up1 = defaultAsset("/npc/oldman_up_1");
        up2 = defaultAsset("/npc/oldman_up_2");
        down1 = defaultAsset("/npc/oldman_down_1");
        down2 = defaultAsset("/npc/oldman_down_2");
        left1 = defaultAsset("/npc/oldman_left_1");
        left2 = defaultAsset("/npc/oldman_left_2");
        right1 = defaultAsset("/npc/oldman_right_1");
        right2 = defaultAsset("/npc/oldman_right_2");
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void initAI() {
        this.goalSelector.add(new AIGoalRandomStroll());
    }

    @Override
    public void initDialogue() {
        this.dialogues.add("Hello, lad!");
        this.dialogues.add("So you've come to this island to find the \ntreasure, eh?");
        this.dialogues.add("I'm sure you've heard of it, but I'm not \nsure what it is.");
        this.dialogues.add("I used to be a great wizard, but \nnow I'm too old to take on any \nmore challenges.");
        this.dialogues.add("Well, good luck young adventurer!");
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}
