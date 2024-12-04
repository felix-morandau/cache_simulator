package service;

import entity.Address;
import entity.CacheResult;
import util.*;
import view.SimulationMessagesPanel;

public class SimulationMessagesPanelManager {
    SimulationMessagesPanel panel;

    public SimulationMessagesPanelManager(SimulationMessagesPanel panel) {
        this.panel = panel;
    }

    public void addMessage(Address address, CacheResult result, ReplacementPolicy policy) {
        StringBuilder message = new StringBuilder();

        message.append("Breaking down address to TIO:\n")
                .append("Tag: ").append(address.getTag()).append("\n")
                .append("Index: ").append(address.getIndex()).append("\n")
                .append("Offset: ").append(address.getOffset()).append("\n\n");

        message.append("Searching for tag ").append(address.getTag()).append("\n");

        message.append("It's a ").append(result.toString()).append("\n");

        if (result == CacheResult.HIT) {
            message.append("Data retrieved directly from cache\n");
        } else {
            message.append("Getting block from main memory to the cache\n");
        }

        if (policy instanceof Fifo) {
            message.append("Updating Fifo policy\n");
        } else if (policy instanceof LRU) {
            message.append("Updating LRU policy\n");
        }

        message.append("Operation completed!\n\n");

        panel.getMessageArea().append(message.toString());
    }

    public void addMessage(Address address, CacheResult result, ReplacementPolicy replacementPolicy, WritePolicy writePolicy, String newByte) {
        StringBuilder message = new StringBuilder();

        message.append("Breaking down address to TIO:\n")
                .append("Tag: ").append(address.getTag()).append("\n")
                .append("Index: ").append(address.getIndex()).append("\n")
                .append("Offset: ").append(address.getOffset()).append("\n\n");

        message.append("Attempting to write byte '").append(newByte).append("' to main memory.\n");

        message.append("It's a ").append(result.toString()).append("\n");

        if (result == CacheResult.HIT) {
            message.append("Cache hit!\n");
        } else {
            message.append("Cache miss.\n");
        }

        message.append("Using write policy: ");
        if (writePolicy instanceof util.WriteBack) {
            message.append("Write-Back.\n");
        } else {
            message.append("Write-Through. Writing data directly in main memory.\n");
        }

        if (replacementPolicy instanceof RandomBlock) {
            message.append("Using random block replacement.\n");
        } else if (replacementPolicy != null) {
            message.append("Updating metadata for ").append(replacementPolicy.getClass().getSimpleName()).append("\n");
        }

        message.append("Write operation completed successfully.\n\n");

        panel.getMessageArea().append(message.toString());
    }

    public void clear() {
        panel.getMessageArea().setText("");
    }
}
