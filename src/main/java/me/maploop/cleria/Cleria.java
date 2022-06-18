package me.maploop.cleria;

import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cleria
{
    public Cleria() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Cleria");
        window.setLocationRelativeTo(null);
        window.setIconImage(AssetHelper.asset("/assets/icon/cleria.png"));
        registerGameObjects();

        GamePanel panel = new GamePanel();
        window.add(panel);

        window.pack();
        window.setVisible(true);

        panel.setupGameObjects();
        panel.startGameThread();

        window.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                GamePanel.collisionChecker.clickMouse(e.getX(), e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                GamePanel.collisionChecker.dragMouse(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                GamePanel.collisionChecker.dragMouse(e.getX(), e.getY());
            }
        });
    }

    private void registerGameObjects() {
        new Player();
    }

    public static void main(String[] args) {
        new Cleria();
    }
}
