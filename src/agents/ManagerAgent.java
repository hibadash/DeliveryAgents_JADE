package agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import ui.DeliveryUI;
import java.awt.Point;
import java.awt.Color;
import java.util.List;

public class ManagerAgent extends Agent {
    private DeliveryUI ui;
    private List<String> clients = List.of("Client1", "Client2", "Client3");

    protected void setup() {
        System.out.println(getLocalName() + " initialized (Manager).");
        ui = (DeliveryUI) getArguments()[0];

      
        ui.addAgent(getLocalName(), new Point(60, 60), Color.BLUE);

        
        addBehaviour(new OneShotBehaviour() {
            public void action() {
                try {
                    Thread.sleep(1500); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Manager assigning deliveries...");

                
                for (int i = 0; i < clients.size(); i++) {
                    String clientName = clients.get(i);
                    String deliveryName = "Delivery" + (i + 1);

                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(getAID(deliveryName));
                    msg.setContent(clientName);
                    send(msg);

                    System.out.println("Assigned " + deliveryName + " to " + clientName);
                }
            }
        });
    }
}
