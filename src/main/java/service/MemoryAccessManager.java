package service;

import entity.*;
import util.Checker;
import view.MemoryAccessPanel;

import javax.swing.*;

public class MemoryAccessManager {
    private MemoryAccessPanel panel;
    private Manager manager;
    private int missCount = 0;
    private int hitCount = 0;

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

    private String getReadAddress() throws IllegalStateException {
        if (!Checker.isValidAddress(panel.getAddressField1().getText())) {
            JOptionPane.showMessageDialog(panel, "Address out of bounds.");
            throw new IllegalStateException("Address out of bounds.");
        }

        return panel.getAddressField1().getText();
    }

    private String getWriteAddress() throws IllegalStateException {
        if (!Checker.isValidAddress(panel.getAddressField2().getText())) {
            JOptionPane.showMessageDialog(panel, "Address out of bounds.");
            throw new IllegalStateException("Address out of bounds.");
        }

        return panel.getAddressField2().getText();
    }

    private String getByte() {
        if (!Checker.isByte(panel.getByteField().getText())) {
            JOptionPane.showMessageDialog(panel, "Byte not valid.");
            throw new NumberFormatException("Byte not valid.");
        }

        return panel.getByteField().getText();
    }

}
