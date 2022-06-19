package me.maploop.cleria.event;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.GameState;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.key.KeyHandler;
import me.maploop.cleria.ui.DialogueUI;

import java.awt.*;

public class EventHandler
{
    Rectangle eventRect;
    int eventRectDefaultX;
    int eventRectDefaultY;

    public EventHandler() {
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(27, 16, "right")) {
            damagePit(GameState.PLAYING);
        }
        if (hit(23, 12, "up")) {
            healingPool(GameState.PLAYING);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        GamePanel.player.hitbox.x = GamePanel.player.worldX + GamePanel.player.hitbox.x;
        GamePanel.player.hitbox.y = GamePanel.player.worldY + GamePanel.player.hitbox.y;
        eventRect.x = eventCol * GamePanel.tileSize * eventRect.x;
        eventRect.y = eventRow * GamePanel.tileSize * eventRect.y;

        if (GamePanel.player.hitbox.intersects(eventRect)) {
            if (GamePanel.player.direction.equals(reqDirection) || reqDirection.equals("any"))
                hit = true;
        }

        GamePanel.player.hitbox.x = GamePanel.player.hitboxDefaultX;
        GamePanel.player.hitbox.y = GamePanel.player.hitboxDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    private void damagePit(int state) {
        GamePanel.gameState = state;
        GamePanel.chat("You drink from the healing pool!", Color.red);
        GamePanel.player.statistic_health -= 1;
    }
    private void healingPool(int state) {
        if (Player.enterPressed) {
            GamePanel.gameState = state;
            GamePanel.chat("You drink from the healing pool!", new Color(253, 133, 255));
            GamePanel.player.statistic_health += 1;
        }
    }
}
