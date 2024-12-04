package service;

import entity.CacheLine;
import entity.CacheMemory;
import view.CacheMemoryPanel;

public class CacheMemoryPanelManager {
    private CacheMemoryPanel panel;

    public CacheMemoryPanelManager(CacheMemoryPanel panel) {
        this.panel = panel;
    }

    public void populateTable(CacheMemory cache) {
        panel.populateData(cache);
    }

    public void colorLine(CacheLine line) {
        panel.colorLine(line);
    }
}
