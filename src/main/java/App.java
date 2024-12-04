import service.*;
import view.MainFrame;

public class App {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Manager manager = new Manager(new SimulationMessagesPanelManager(frame.getSimulationMessagesPanel()), new MainMemoryPanelManager(frame.getMainMemoryPanel()), new CacheMemoryPanelManager(frame.getCacheMemoryPanel()));
        new MemoryAccessManager(frame.getMemoryAccessPanel(), manager);
        new SystemParametersPanelManager(frame.getSystemParametersPanel(), manager);
        new CommandsPanelManager(frame.getCommandsPanel(), manager);
    }
}
