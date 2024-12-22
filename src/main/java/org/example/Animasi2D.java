package org.example;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Animasi2D extends JPanel {

    private int x = 0;
    private int y = 0;
    private int deltaX = 2;
    private int deltaY = 2;

    public Animasi2D() {
        this.setPreferredSize(new Dimension(800, 400));
        this.setBackground(Color.WHITE);
        Timer timer = new Timer(10, e -> {
            x += deltaX;
            y += deltaY;
            // Pantulan jika mencapai tepi panel
            if (x < 0 || x + 50 > getWidth()) deltaX = -deltaX;
            if (y < 0 || y + 50 > getHeight()) deltaY = -deltaY;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.fillOval(x, y, 50, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Aplikasi Komputer Grafik - Ryu Kurnianto Putra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Animasi2D panel = new Animasi2D();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
