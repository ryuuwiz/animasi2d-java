package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class PolishCowAnimation extends JPanel implements ActionListener {
    private int cowX = 50;
    private int cowY = 200;
    private double cowAngle = 0;
    private int sunX = 0; // Posisi matahari
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

        // Draw grass
        drawGrass(g2d);

        // Draw sun
        drawSun(g2d);

        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Cow body
        g2d.setColor(Color.WHITE);
        g2d.fillOval(cowX, cowY, 100, 60);

        // Cow spots
        g2d.setColor(Color.BLACK);
        g2d.fillOval(cowX + 20, cowY + 10, 20, 20);
        g2d.fillOval(cowX + 60, cowY + 20, 15, 15);

        // Cow head
        g2d.setColor(Color.WHITE);
        g2d.fillOval(cowX + 100, cowY - 20, 40, 40);

        // Cow ears
        g2d.setColor(Color.BLACK);
        g2d.fillOval(cowX + 110, cowY - 30, 10, 20);
        g2d.fillOval(cowX + 130, cowY - 30, 10, 20);

        // Rotate for dancing effect
        g2d.rotate(Math.toRadians(cowAngle), cowX + 50, cowY + 30);

        // Cow legs
        g2d.setColor(Color.BLACK);
        g2d.fillRect(cowX + 10, cowY + 60, 10, 30);
        g2d.fillRect(cowX + 80, cowY + 60, 10, 30);

        // Restore original transform
        g2d.setTransform(originalTransform);
    }

    private void drawGrass(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34)); // Green color for grass
        // Draw some grass blades
        g2d.fillOval(0, 250, 10, 30);
        g2d.fillOval(20, 240, 10, 30);
        g2d.fillOval(40, 250, 10, 30);
        g2d.fillOval(60, 240, 10, 30);
        g2d.fillOval(80, 250, 10, 30);
        g2d.fillOval(100, 240, 10, 30);
        g2d.fillOval(120, 250, 10, 30);
        g2d.fillOval(140, 240, 10, 30);
        g2d.fillOval(160, 250, 10, 30);
        g2d.fillOval(180, 240, 10, 30);
    }

    private void drawSun(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunX, 20, 50, 50); // Draw the sun
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            // Animate dancing movement
            cowAngle = 10 * Math.sin(System.currentTimeMillis() / 100.0);
            cowX += 2;

            // Move the sun
            sunX += 1; // Move the sun to the right

            // Reset position when cow moves off-screen
            if (cowX > getWidth()) {
                cowX = -150;
            }

            // Reset sun position when it moves off-screen
            if (sunX > getWidth()) {
                sunX = -50; // Reset sun position to the left
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