package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DeliveryUI extends JFrame {
    private final Map<String, Point> agentPositions = new HashMap<>();
    private final Map<String, Color> agentColors = new HashMap<>();
    private final Map<String, String> deliveryLinks = new HashMap<>();

    public DeliveryUI() {
        setTitle("JADE Delivery Simulation");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        new Timer(80, e -> repaint()).start();
    }

    public void addAgent(String name, Point pos, Color color) {
        agentPositions.put(name, new Point(pos));
        agentColors.put(name, color);
    }

    public void updateAgentPosition(String name, Point pos) {
        agentPositions.put(name, new Point(pos));
    }

    public Point getAgentPosition(String name) {
        return agentPositions.get(name);
    }

    public void setDeliveryLink(String delivery, String client) {
        deliveryLinks.put(delivery, client);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        for (var entry : deliveryLinks.entrySet()) {
            String delivery = entry.getKey();
            String client = entry.getValue();

            Point p1 = agentPositions.get(delivery);
            Point p2 = agentPositions.get(client);

            if (p1 != null && p2 != null) {
                g2.setColor(Color.GRAY);
                drawArrow(g2, p1.x + 10, p1.y + 10, p2.x + 10, p2.y + 10);
            }
        }

        for (var entry : agentPositions.entrySet()) {
            String name = entry.getKey();
            Point p = entry.getValue();
            Color color = agentColors.getOrDefault(name, Color.BLACK);
            g2.setColor(color);
            g2.fillOval(p.x, p.y, 20, 20);
            g2.setColor(Color.BLACK);
            g2.drawString(name, p.x - 10, p.y - 5);
        }
    }

    private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Graphics2D gSave = (Graphics2D) g2.create();
        gSave.translate(x1, y1);
        gSave.rotate(angle);
        gSave.drawLine(0, 0, len, 0);
        gSave.fillPolygon(new int[]{len, len - 10, len - 10, len},
                          new int[]{0, -5, 5, 0}, 4);
        gSave.dispose();
    }
}
