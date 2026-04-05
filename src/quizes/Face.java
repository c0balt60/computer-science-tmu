package quizes;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Face extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Face");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new Face();
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public Face() {
        super();
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw body
        g.setColor(new Color(0xd9ceab));
        g.fillRoundRect(125, 125, 250, 250, 200, 200);
        g.setColor(Color.GRAY);
        g.drawRoundRect(125, 125, 250, 250, 200, 200);

        // Draw mouth
        g.fillOval(200, 300, 100, 50);

        // Draw eyes
        int x = 175;
        int y = 175;
        int w = 50;
        int h = 50;
        int dist = 100;

        g.fillOval(x, y, w, h);
        g.fillOval(x + dist, y, w, h);

        // Eyebrows
        g.setStroke(new BasicStroke(2f));
        g.drawLine(x - 2, y - 20, x + 50, y - 7);
        g.drawLine(x + dist - 2, y - 7, x + dist + 50, y - 20);
    }
}
