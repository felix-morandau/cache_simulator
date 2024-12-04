package view;

import javax.swing.*;
import java.awt.*;

public class SimulationMessagesPanel extends JPanel {
    private JTextArea messageArea;

    public SimulationMessagesPanel() {
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public void refreshMessages(String newMessages) {
        messageArea.setText(newMessages);
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }
}
