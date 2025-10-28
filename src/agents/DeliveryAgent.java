package agents;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import ui.DeliveryUI;
import java.awt.Color;
import java.awt.Point;

public class DeliveryAgent extends Agent {
    private DeliveryUI ui;
    private Point position = new Point(60, 60);
    private Point destination = null;
    private String clientTarget = null;

    protected void setup() {
        ui = (DeliveryUI) getArguments()[0];
        ui.addAgent(getLocalName(), position, Color.ORANGE);

        System.out.println(getLocalName() + " waiting for client assignment...");

        addBehaviour(new TickerBehaviour(this, 150) {
            protected void onTick() {
                
                ACLMessage msg = receive();
                if (msg != null) {
                    clientTarget = msg.getContent();
                    System.out.println(getLocalName() + " received delivery target: " + clientTarget);

                    
                    destination = ui.getAgentPosition(clientTarget);
                }

                if (destination != null) {
                    moveTowards(destination);
                    ui.updateAgentPosition(getLocalName(), position);

                    
                    if (position.distance(destination) < 5) {
                        System.out.println(getLocalName() + " reached " + clientTarget + "!");
                        stop();
                    }
                }
            }
        });
    }

    private void moveTowards(Point dest) {
        if (position.x < dest.x) position.x += 5;
        if (position.x > dest.x) position.x -= 5;
        if (position.y < dest.y) position.y += 5;
        if (position.y > dest.y) position.y -= 5;
    }
}
