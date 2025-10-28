package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DeliveryUI extends JFrame {
    private final Map<String, Point> agentPositions = new HashMap<>();
    private final Map<String, Color> agentColors = new HashMap<>();

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (var entry : agentPositions.entrySet()) {
            String name = entry.getKey();
            Point p = entry.getValue();
            Color color = agentColors.getOrDefault(name, Color.BLACK);
            g.setColor(color);
            g.fillOval(p.x, p.y, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(name, p.x - 10, p.y - 5);
        }
    }
}
