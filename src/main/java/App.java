import service.*;
import view.MainFrame;

public class App {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Manager manager = new Manager(new SimulationMessagesPanelManager(frame.getSimulationMessagesPanel()), new MainMemoryPanelManager(frame.getMainMemoryPanel()), new CacheMemoryPanelManager(frame.getCacheMemoryPanel()));
        MemoryAccessManager memoryAccessManager = new MemoryAccessManager(frame.getMemoryAccessPanel(), manager);
        new SystemParametersPanelManager(frame.getSystemParametersPanel(), manager, memoryAccessManager);
        new CommandsPanelManager(frame.getCommandsPanel(), manager, memoryAccessManager);
    }
}
