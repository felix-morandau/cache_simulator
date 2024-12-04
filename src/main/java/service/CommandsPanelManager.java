package service;

import entity.MemoryCell;
import util.Checker;
import view.CommandsPanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class CommandsPanelManager {
    private Manager manager;
    private CommandsPanel panel;

    public CommandsPanelManager(CommandsPanel panel, Manager manager) {
        this.manager = manager;
        this.panel = panel;
        buttonFunctionality();
    }

    public void buttonFunctionality() {
        panel.getExecuteButton().addActionListener(_ -> executeCommands());
        panel.getLoadButton().addActionListener(_ -> loadFile());
    }

    private void executeCommands() {
        String[] commands = panel.getCommandsArea().getText().split("\n");

        for (String command : commands) {
            if (!Checker.validateCommand(command)) {
                JOptionPane.showMessageDialog(panel, "Wrong format: " + command);
                return;
            }
        }

        for (String command : commands) {
            command = command.toLowerCase();

            String[] splitCommand = command.split("\\s+");

            String operation = splitCommand[0];
            String address = splitCommand[1];

            if (operation.equals("read")) {
                manager.readOperation(address);
                manager.setCachePanel();
            } else {
                MemoryCell data = new MemoryCell(splitCommand[2]);
                manager.writeOperation(address, data);
                manager.setMainMemory();
            }
        }

    }

    private void loadFile() {
        File file = new File("commands.txt");

        try {
            String commands = Files.readString(file.toPath()).trim();
            commands = commands.replace("\r\n", "\n");
            panel.getCommandsArea().setText(commands);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}