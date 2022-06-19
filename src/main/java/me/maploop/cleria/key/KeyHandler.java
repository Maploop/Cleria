package me.maploop.cleria.key;

import me.maploop.cleria.GamePanel;
import me.maploop.cleria.entity.Entity;
import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.ui.NoticeUI;
import me.maploop.cleria.ui.TitleScreenUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e) {
        if (GamePanel.chatOpen) {
            GamePanel.chatMessage += e.getKeyChar();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (GamePanel.gameState == GamePanel.TITLE_SCREEN) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (TitleScreenUI.commandNum < 0) {
                    TitleScreenUI.commandNum = 2;
                } else {
                    TitleScreenUI.commandNum--;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (TitleScreenUI.commandNum >= 2) {
                    TitleScreenUI.commandNum = 0;
                } else {
                    TitleScreenUI.commandNum++;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (TitleScreenUI.commandNum <= 0) {
                    TitleScreenUI.commandNum = 0;
                }
                switch (TitleScreenUI.commandNum) {
                    case 0:
                        GamePanel.gameState = GamePanel.PLAYING;
                        GamePanel.playMusic("cleria");
                        break;
                    case 1:
                        NoticeUI.noticeMessage = "This feature is not finished yet. \nPlease wait for the next update.";
                        NoticeUI.noticeColor = Color.yellow;
                        int previousState = GamePanel.gameState;
                        GamePanel.gameState = GamePanel.NOTICE;
                        new Timer().schedule(new TimerTask()
                        {
                            @Override
                            public void run() {
                                GamePanel.gameState = previousState;
                            }
                        }, 3000);
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
            return;
        }
        if (!GamePanel.chatOpen && (GamePanel.gameState == GamePanel.PLAYING))
            Entity.gameObjectRegistry.values().forEach(v -> v.keyPressed(code));

        if (e.getKeyCode() == 8) {
            if (GamePanel.chatOpen) {
                StringBuilder buffer = new StringBuilder(GamePanel.chatMessage);
                try {
                    buffer.deleteCharAt(buffer.length() - 1);
                } catch (Exception ignored) { }
                GamePanel.chatMessage = buffer.toString();
                return;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Player.enterPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_T && GamePanel.gameState == GamePanel.PLAYING) {
            if (GamePanel.chatOpen) {
                if (GamePanel.chatMessage != "") {
                    GamePanel.chat("<Player> " + GamePanel.chatMessage);
                    GamePanel.chatMessage = "";
                }
                GamePanel.chatOpen = false;
            } else {
                GamePanel.chatOpen = true;
            }
        }
        if (GamePanel.gameState == GamePanel.DIALOGUE) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                GamePanel.gameState = GamePanel.PLAYING;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (GamePanel.chatOpen) {
                GamePanel.chatOpen = false;
                return;
            }
            if (GamePanel.gameState == GamePanel.PAUSED || GamePanel.gameState == GamePanel.DIALOGUE) {
                GamePanel.gameState = GamePanel.PLAYING;
            } else {
                GamePanel.gameState = GamePanel.PAUSED;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (!GamePanel.chatOpen && (GamePanel.gameState == GamePanel.PLAYING))
            Entity.gameObjectRegistry.values().forEach(v -> v.keyReleased(code));
    }
}
