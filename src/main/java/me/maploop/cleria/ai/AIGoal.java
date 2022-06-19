package me.maploop.cleria.ai;

import me.maploop.cleria.entity.Entity;

public abstract class AIGoal
{
    public int counter;

    public void update(Entity entity) {
        counter++;
        if (counter >= updateInterval()) {
            counter = 0;
            updateAI(entity);
        }
    }

    public abstract void updateAI(Entity entity);
    public abstract int updateInterval();
}
