package me.maploop.cleria.ai;

import me.maploop.cleria.entity.Entity;

import java.util.Random;

public class AIGoalRandomStroll extends AIGoal
{
    @Override
    public void updateAI(Entity entity) {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        if (i <= 25)
            entity.direction = "up";
        if (i > 25 && i <= 50)
            entity.direction = "down";
        if (i > 50 && i <= 75)
            entity.direction = "left";
        if (i > 75)
            entity.direction = "right";
    }

    @Override
    public int updateInterval() {
        return 120;
    }
}
