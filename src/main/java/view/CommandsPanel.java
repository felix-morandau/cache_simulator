package view;

import javax.swing.*;
import java.awt.*;

public class CommandsPanel extends JPanel {
    private JTextArea commandsArea;
    private JButton executeButton;
    private JButton loadButton;

    public CommandsPanel() {
        setLayout(new BorderLayout());

        commandsArea = new JTextArea(10, 30);
        commandsArea.setLineWrap(true);
        commandsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(commandsArea);

        executeButton = new JButton("Execute");
        loadButton = new JButton(" Load ");

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loadButton);
        buttonPanel.add(executeButton);

        add(new JLabel("Enter Commands Below:", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextArea getCommandsArea() {
        return commandsArea;
    }

    public JButton getExecuteButton() {
        return executeButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
