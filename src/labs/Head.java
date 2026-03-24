package labs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Head extends JPanel {

    private boolean isHovered = false;

    public Head() {
        super();
        setPreferredSize(new Dimension(500, 500));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse Entered");
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse left");
                isHovered = false;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw body
        g.setColor(Color.GRAY);
        g.drawRoundRect(125, 125, 250, 250, 200, 200);

        // Draw mouth
        g.drawOval(200, 300, 100, 50);

        // Draw eyes
        int x = 175;
        int y = 175;
        int w = 50;
        int h = 50;
        int dist = 100;
        if (isHovered) {
            // Draw ovals
            g.drawOval(x, y, w, h);
            g.drawOval(x + dist, y, w, h);
        } else {
            // Draw "slits"
            g.drawArc(x, y, w, h, 20, 130);
            g.drawArc(x + dist, y, w, h, 30, 130);
        }
    }

}
