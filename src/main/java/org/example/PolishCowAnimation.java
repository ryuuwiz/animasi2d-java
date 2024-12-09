package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class PolishCowAnimation extends JPanel implements ActionListener {
    private int x = 50;
    private int y = 200;
    private double angle = 0;
    private boolean isMoving = true;
    private Timer timer;

    public PolishCowAnimation() {
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smoother animation
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Cow body
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, 100, 60);

        // Cow spots
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 20, y + 10, 20, 20);
        g2d.fillOval(x + 60, y + 20, 15, 15);

        // Cow head
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + 100, y - 20, 40, 40);

        // Cow ears
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 110, y - 30, 10, 20);
        g2d.fillOval(x + 130, y - 30, 10, 20);

        // Rotate for dancing effect
        g2d.rotate(Math.toRadians(angle), x + 50, y + 30);

        // Cow legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 10, y + 60, 10, 30);
        g2d.fillRect(x + 80, y + 60, 10, 30);

        // Restore original transform
        g2d.setTransform(originalTransform);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            // Animate dancing movement
            angle = 10 * Math.sin(System.currentTimeMillis() / 100.0);
            x += 2;

            // Reset position when cow moves off-screen
            if (x > getWidth()) {
                x = -150;
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polish Cow Dancing Animation");
        PolishCowAnimation animation = new PolishCowAnimation();
        frame.add(animation);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
