package agents;

import jade.core.Agent;
import ui.DeliveryUI;
import java.awt.Point;
import java.awt.Color;

public class ClientAgent extends Agent {
    private DeliveryUI ui;
    private Point position;

    protected void setup() {
        ui = (DeliveryUI) getArguments()[0];

        switch (getLocalName()) {
            case "Client1": position = new Point(400, 280); break;
            case "Client2": position = new Point(480, 100); break;
            case "Client3": position = new Point(300, 350); break;
            default: position = new Point(400, 280);
        }

        ui.addAgent(getLocalName(), position, Color.GREEN);
    }

    public Point getPosition() {
        return position;
    }
}
