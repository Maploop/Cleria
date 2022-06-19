package me.maploop.cleria.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIButton
{
    public String label;
    public int x, y, width, height;
    public Runnable onClick;
    public boolean in = false;

    public UIButton(String label, int x, int y, int width, int height) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = () -> {};
    }

    public UIButton setFunction(Runnable runnable) {
        this.onClick = runnable;
        return this;
    }

    public void draw(JPanel panel, Graphics2D g2d) {
        g2d.setColor(new Color(54, 71, 63));
        Rectangle rect = new Rectangle(x, y, width, height);
        g2d.fill(rect);
        g2d.setColor(new Color(255, 255, 255));
        g2d.draw(rect);
        g2d.setColor(new Color(255, 255, 255));
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.drawString(label, x + (width / 2) - (label.length() * 5), y + (height / 2) + 5);
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                if (rect.contains(e.getPoint())) {
                    onClick.run();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                in = e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height;
                if (in) {
                    g2d.setColor(new Color(77, 92, 83));
                } else {
                    g2d.setColor(new Color(54, 71, 63));
                }
                g2d.fill(rect);
                g2d.setColor(new Color(255, 255, 255));
                g2d.draw(rect);
            }
        });
    }
}
