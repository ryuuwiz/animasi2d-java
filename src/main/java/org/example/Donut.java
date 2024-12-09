package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Donut extends JPanel implements ActionListener {
    private float A = 0, B = 0;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public Donut() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        // Create a timer to update the animation
        Timer timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics gp) {
        super.paintComponent(gp);
        Graphics2D g2d = (Graphics2D) gp;

        // Rendering calculations
        float[] z = new float[WIDTH * HEIGHT];
        char[] b = new char[WIDTH * HEIGHT];

        for (float j = 0; j < 6.28; j += 0.07) {
            for (float i = 0; i < 6.28; i += 0.02) {
                float c = (float) Math.sin(i);
                float d = (float) Math.cos(j);
                float e = (float) Math.sin(A);
                float f = (float) Math.sin(j);
                float g = (float) Math.cos(A);
                float h = d + 2;
                float D = 1 / (c * h * e + f * g + 5);
                float l = (float) Math.cos(i);
                float m = (float) Math.cos(B);
                float n = (float) Math.sin(B);
                float t = c * h * g - f * e;

                int x = WIDTH / 2 + (int)(150 * D * (l * h * m - t * n));
                int y = HEIGHT / 2 + (int)(100 * D * (l * h * n + t * m));

                int o = x + WIDTH * y;
                int N = (int)(8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));

                if (0 <= y && y < HEIGHT && 0 <= x && x < WIDTH && D > z[o]) {
                    z[o] = D;

                    // Choose color based on depth
                    Color donutColor = getColorForDepth(N, D);
                    g2d.setColor(donutColor);
                    g2d.fillRect(x, y, 2, 2);
                }
            }
        }
    }

    // Create a color gradient based on depth and angle
    private Color getColorForDepth(int N, float D) {
        // Create a color gradient
        int red = (int)(Math.abs(Math.sin(N)) * 255);
        int green = (int)(Math.abs(Math.cos(D)) * 255);
        int blue = (int)(Math.abs(Math.tan(N * D)) * 255);

        return new Color(
                Math.min(red, 255),
                Math.min(green, 255),
                Math.min(blue, 255)
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update rotation angles
        A += 0.04;
        B += 0.02;

        // Repaint the panel
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Donut Animation");
            Donut donutPanel = new Donut();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(donutPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
