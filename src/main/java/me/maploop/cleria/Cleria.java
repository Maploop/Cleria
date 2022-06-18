package me.maploop.cleria;

import me.maploop.cleria.entity.objects.Player;

import javax.swing.*;

public class Cleria
{
    public Cleria() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Cleria");
        window.setLocationRelativeTo(null);
        registerGameObjects();

        GamePanel panel = new GamePanel();
        window.add(panel);

        window.pack();
        window.setVisible(true);

        panel.setupGameObjects();
        panel.startGameThread();
    }

    private void registerGameObjects() {
        new Player();
    }

    public static void main(String[] args) {
        new Cleria();
    }
}
