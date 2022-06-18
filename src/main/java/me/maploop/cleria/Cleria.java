package me.maploop.cleria;

import me.maploop.cleria.entity.objects.Player;
import me.maploop.cleria.helper.AssetHelper;

import javax.swing.*;

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
    }

    private void registerGameObjects() {
        new Player();
    }

    public static void main(String[] args) {
        new Cleria();
    }
}
