package service;

import entity.SimulationState;
import entity.SystemParameters;
import util.*;
import view.SystemParametersPanel;

import javax.swing.*;
import java.util.Objects;

public class SystemParametersPanelManager {

    private final MemoryAccessManager memoryAccessManager;
    private final SystemParametersPanel panel;
    private SimulationState state = SimulationState.START;
    private int step = 1;
    private SystemParameters parameters;
    private Manager manager;

    public SystemParametersPanelManager(SystemParametersPanel panel, Manager manager, MemoryAccessManager memoryAccessManager) {
        this.panel = panel;
        this.manager = manager;
        this.memoryAccessManager = memoryAccessManager;
        buttonFunctionality();
    }

    /**
     * Adds action listeners to the buttons in the panel.
     */
    public void buttonFunctionality() {
        panel.getNextButton().addActionListener(_ -> handleButtonClick());
    }

    /**
     * Handles button functionality dynamically based on simulation state.
     */
    private void handleButtonClick() {
        if (state == SimulationState.START || state == SimulationState.IN_PROGRESS) {
            startSimulation();
        } else if (state == SimulationState.EXPLAIN) {
            executeNextStep(step++);
        }
    }

    /**
     * Handles the "Start" button functionality to initialize the simulation.
     */
    private void startSimulation() {
        applySystemParameters();

        if (!Checker.isCacheSizeValid()) {
            JOptionPane.showMessageDialog(panel, "Cache size is smaller than main memory size.");
            throw new IllegalStateException("Cache size is smaller than main memory size.");
        }

        manager.initializeSimulation();
        memoryAccessManager.initializeSimulation();
        panel.getLogArea().setText(SystemParameters.getInstance().getFirstCalculation());

        step = 1;
        state = panel.getExplainCheckBox().isSelected() ? SimulationState.EXPLAIN : SimulationState.IN_PROGRESS;
        panel.getNextButton().setText(panel.getExplainCheckBox().isSelected() ? "Next" : "Reset Simulation");
    }

    /**
     * Handles the "Next" button functionality to execute the next step in the simulation.
     */
    private void executeNextStep(int step) {

        switch(step) {
            case 1 -> {
                panel.getLogArea().setText(parameters.getSecondCalculation());
            }
            case 2 -> {
                panel.getLogArea().setText(parameters.getThirdCalculation());
                state = SimulationState.IN_PROGRESS;
                panel.getNextButton().setText("Reset Simulation");
            }

        }

    }

    /**
     * Applies user-selected system parameters to the simulation.
     */
    private void applySystemParameters() {
        int addressWidth = getAddressWidth();
        int blockSize = getBlockSize();
        int cacheSize = getCacheSize();
        int associativity = getAssociativity();

        if (parameters == null) {
            parameters = SystemParameters.getInstance(addressWidth, cacheSize, blockSize, associativity,
                    getWritePolicy(), getReplacementPolicy());
        } else {
            parameters.setParameters(addressWidth, cacheSize, blockSize, associativity,
                    getWritePolicy(), getReplacementPolicy());
        }
    }

    private int getAddressWidth() {
        return Integer.parseInt(Objects.requireNonNull(panel.getAddressWidthCombo().getSelectedItem()).toString());
    }

    private int getBlockSize() {
        return panel.getSelectedBlockSize();
    }

    private int getCacheSize() {
        return Integer.parseInt(Objects.requireNonNull(panel.getCacheSizeCombo().getSelectedItem()).toString());
    }

    private int getAssociativity() {
        return panel.getSelectedAssociativity();
    }

    private WritePolicy getWritePolicy() {
        String policy = Objects.requireNonNull(panel.getWriteHitCombo().getSelectedItem()).toString();
        return "Write back".equals(policy) ? new WriteBack() : new WriteThrough();
    }

    private ReplacementPolicy getReplacementPolicy() {
        String policy = Objects.requireNonNull(panel.getReplacementCombo().getSelectedItem()).toString();
        return switch (policy) {
            case "Least Recently Used" -> new LRU();
            case "First In First Out" -> new Fifo();
            default -> new RandomBlock();
        };
    }
}
