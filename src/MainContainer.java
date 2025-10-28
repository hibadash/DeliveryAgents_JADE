import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import ui.DeliveryUI;

public class MainContainer {
    public static void main(String[] args) {
        try {
            Runtime rt = Runtime.instance();
            Profile p = new ProfileImpl();
            p.setParameter(Profile.GUI, "true");
            AgentContainer mainContainer = rt.createMainContainer(p);

            DeliveryUI ui = new DeliveryUI();
            ui.setVisible(true);

            // Création du Manager
            AgentController manager = mainContainer.createNewAgent("Manager", "agents.ManagerAgent", new Object[]{ui});
            manager.start();

            // Création 3 Clients
            for (int i = 1; i <= 3; i++) {
                AgentController client = mainContainer.createNewAgent("Client" + i, "agents.ClientAgent", new Object[]{ui});
                client.start();
            }

            // création 3 Delivery Agents
            for (int i = 1; i <= 3; i++) {
                AgentController delivery = mainContainer.createNewAgent("Delivery" + i, "agents.DeliveryAgent", new Object[]{ui});
                delivery.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
