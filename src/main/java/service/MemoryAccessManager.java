package service;

import entity.*;
import view.MemoryAccessPanel;
import view.SimulationMessagesPanel;

public class MemoryAccessManager {
    private MemoryAccessPanel panel;
    private Manager manager;
    private int missCount = 0;
    private int hitCount = 0;
    private SimulationState state = SimulationState.START;

    public MemoryAccessManager(MemoryAccessPanel panel, Manager manager) {
        this.panel = panel;
        this.manager = manager;
        buttonFunctionality();
    }

    private void buttonFunctionality() {
        panel.getReadButton().addActionListener(_ -> readFunctionality());
        panel.getWriteButton().addActionListener(_ -> writeFunctionality());
        panel.getFlushButton().addActionListener(_ -> flushFunctionality());
    }

    private void readFunctionality() {
        CacheResult operationResult = manager.readOperation(getReadAddress());
        manager.setCachePanel();

        if (operationResult == CacheResult.HIT) {
            hitCount++;
        } else {
            missCount++;
        }

        panel.updateAddress(manager.getAddressFromHexString(getReadAddress()));
        panel.updateLabels(hitCount, missCount);
    }

    private void writeFunctionality() {
        CacheResult operationResult = manager.writeOperation(getWriteAddress(), new MemoryCell(getByte()));
        manager.setCachePanel();
        manager.setMainMemory();

        if (operationResult == CacheResult.HIT) {
            hitCount++;
        } else {
            missCount++;
        }
    }

    private void flushFunctionality() {
        manager.initializeSimulation();
        missCount = 0;
        hitCount = 0;
    }

    public void disableButtons() {
        panel.getReadButton().setEnabled(false);
        panel.getWriteButton().setEnabled(false);
        panel.getFlushButton().setEnabled(false);
    }

    public void enableButtons() {
        panel.getReadButton().setEnabled(true);
        panel.getWriteButton().setEnabled(true);
        panel.getFlushButton().setEnabled(true);
    }

    private String getReadAddress() {
        return panel.getAddressField1().getText();
    }

    private String getWriteAddress() {
        return panel.getAddressField2().getText();
    }

    private String getByte() {
        return panel.getByteField().getText();
    }

}
