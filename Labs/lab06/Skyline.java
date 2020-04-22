
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;

public class Skyline extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Skyline");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Skyline());
        frame.pack();
        frame.setVisible(true);
    }

    public Skyline() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics gOri) {
        Graphics2D g = (Graphics2D) gOri;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint sunSet = new GradientPaint(0, 0, Color.BLACK, 0, HEIGHT, new Color(0, 0, 55));
        g.setPaint(sunSet);
        g.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        Random rand = new Random();
        // Your code here
        int height = rand.nextInt(11) + 95;
        g.setColor(Color.GRAY);
        int[] horizonHeights = new int[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            g.drawLine(i, HEIGHT - height, i, HEIGHT);
            int deltaHeight = rand.nextInt(6);
            if (rand.nextInt(2) == 1)
                deltaHeight *= -1;
            height += deltaHeight;
            horizonHeights[i] = height;
            // System.out.println(height);
        }
        for (int i = 0; i < 100; i++) {
            int starWidth = rand.nextInt(WIDTH) - 2;
            int starHeight = rand.nextInt(HEIGHT - horizonHeights[starWidth]);
            g.setColor(Color.WHITE);
            g.fillRect(starWidth, starHeight - 2, 2, 2);
        }
        int clusterCenterX = rand.nextInt(WIDTH);
        int clusterCenterY = rand.nextInt(HEIGHT - horizonHeights[clusterCenterX]) - 25;
        for (int i = 0; i < 100; i++)
            g.fillRect(clusterCenterX + (int) (rand.nextGaussian() * 25),
                    clusterCenterY + (int) (rand.nextGaussian() * 25), 2, 2);

    }
}
