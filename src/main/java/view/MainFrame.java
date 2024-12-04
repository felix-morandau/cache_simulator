package view;

import entity.CacheMemory;
import entity.MainMemory;
import entity.SystemParameters;
import util.Fifo;
import util.WriteThrough;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private SystemParametersPanel systemParametersPanel;
    private MemoryAccessPanel memoryAccessPanel;
    private SimulationMessagesPanel simulationMessagesPanel;
    private CacheMemoryPanel cacheMemoryPanel;
    private MainMemoryPanel mainMemoryPanel;
    private CommandsPanel commandsPanel;

    public MainFrame() {
        setTitle("Simulation Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Adjusted size for better visibility

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Added padding for better spacing

        // Initialize panels
        cacheMemoryPanel = new CacheMemoryPanel();
        mainMemoryPanel = new MainMemoryPanel();
        systemParametersPanel = new SystemParametersPanel();
        memoryAccessPanel = new MemoryAccessPanel();
        simulationMessagesPanel = new SimulationMessagesPanel();
        commandsPanel = new CommandsPanel();

        // Add SystemParametersPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gbc.weightx = 0.3; // Proportional space allocation
        gbc.weighty = 1.0;
        add(systemParametersPanel, gbc);

        // Add MemoryAccessPanel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.5;
        add(memoryAccessPanel, gbc);

        //Add CommandsPanel
        gbc.gridx = 3; // Place it in the column right next to MemoryAccessPanel
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        gbc.weightx = 0.2; // Allocate space for CommandsPanel
        add(commandsPanel, gbc);

        // Add SimulationMessagesPanel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weighty = 0.5;
        add(simulationMessagesPanel, gbc);

        // Add CacheMemoryPanel
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(cacheMemoryPanel, gbc);

        // Add MainMemoryPanel
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        add(mainMemoryPanel, gbc);

        pack(); // Automatically adjust sizes
        setVisible(true);
    }

    public SystemParametersPanel getSystemParametersPanel() {
        return systemParametersPanel;
    }

    public MemoryAccessPanel getMemoryAccessPanel() {
        return memoryAccessPanel;
    }

    public SimulationMessagesPanel getSimulationMessagesPanel() {
        return simulationMessagesPanel;
    }

    public CacheMemoryPanel getCacheMemoryPanel() {
        return cacheMemoryPanel;
    }

    public MainMemoryPanel getMainMemoryPanel() {
        return mainMemoryPanel;
    }

    public CommandsPanel getCommandsPanel() {
        return commandsPanel;
    }

    public void setCacheMemoryPanel(CacheMemoryPanel cacheMemoryPanel) {
        this.cacheMemoryPanel = cacheMemoryPanel;
    }

    public void setMainMemoryPanel(MainMemoryPanel mainMemoryPanel) {
        this.mainMemoryPanel = mainMemoryPanel;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
