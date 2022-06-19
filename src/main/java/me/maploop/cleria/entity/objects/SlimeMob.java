package me.maploop.cleria.entity.objects;

import me.maploop.cleria.ai.AIGoalRandomStroll;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.entity.MonsterEntity;

public class SlimeMob extends Entity implements MonsterEntity
{
    public SlimeMob() {
        super("slime", 0, 0, 0, 0, 1);
    }

    @Override
    public void initStatistics() {
        statistic_maxHealth = 4;
        hitbox.x = 3;
        hitbox.y = 18;
        hitbox.width = 42;
        hitbox.height = 30;

        hitboxDefaultX = 3;
        hitboxDefaultY = 18;
    }

    @Override
    public void initImage() {
        up1 = defaultAsset("/slime/slime_down_1");
        up2 = defaultAsset("/slime/slime_down_2");
        down1 = defaultAsset("/slime/slime_down_1");
        down2 = defaultAsset("/slime/slime_down_2");
        left1 = defaultAsset("/slime/slime_down_1");
        left2 = defaultAsset("/slime/slime_down_2");
        right1 = defaultAsset("/slime/slime_down_1");
        right2 = defaultAsset("/slime/slime_down_2");
    }

    @Override
    public void initAI() {
        this.goalSelector.add(new AIGoalRandomStroll());

    }
}
