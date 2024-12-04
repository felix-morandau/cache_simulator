package service;

import entity.Address;
import entity.MainMemory;
import view.MainMemoryPanel;

public class MainMemoryPanelManager {
    private MainMemoryPanel panel;

    public MainMemoryPanelManager(MainMemoryPanel panel) {
        this.panel = panel;
    }

    public void populateTable(MainMemory mainMemory) {
        panel.populateTableData(mainMemory);
    }

    public void colorCell(Address address) {
        panel.colorCell(address);
    }
}
