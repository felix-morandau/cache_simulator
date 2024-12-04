package view;

import entity.Address;
import entity.AddressTemplate;

import javax.swing.*;
import java.awt.*;

public class MemoryAccessPanel extends JPanel {

    private JButton readButton;
    private JButton writeButton;
    private JButton flushButton;
    private JTextField addressField1;
    private JTextField addressField2;
    private JTextField byteField;
    private JLabel cacheHitsLabel;
    private JLabel cacheMissesLabel;
    private JLabel cacheHitsNumberLabel;
    private JLabel cacheMissesNumberLabel;
    private JLabel tagValueLabel;
    private JLabel indexValueLabel;
    private JLabel offsetValueLabel;

    public MemoryAccessPanel() {
        setLayout(new GridBagLayout());
        initPanel();
    }

    private void initPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Manual Memory Access Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        add(new JLabel("Manual Memory Access:"), gbc);

        // Initialize Buttons
        readButton = new JButton("Read");
        writeButton = new JButton("Write");
        flushButton = new JButton("Flush");

        // Add Read Button
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(readButton, gbc);

        // Add Write Button
        gbc.gridy = 2;
        add(writeButton, gbc);

        // Add Flush Button
        gbc.gridy = 3;
        add(flushButton, gbc);

        // Address field with label
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(new JLabel("Addr: 0x"), gbc);

        addressField1 = new JTextField(5);
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        add(addressField1, gbc);

        // Second address field for writing
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Addr: 0x"), gbc);

        addressField2 = new JTextField(5);
        gbc.gridx = 3;
        add(addressField2, gbc);

        // Byte field with label
        gbc.gridx = 4;
        add(new JLabel(", Byte: 0x"), gbc);

        byteField = new JTextField(3);
        gbc.gridx = 5;
        add(byteField, gbc);

        // Tag, Index, Offset Panel
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 6;
        JPanel tagPanel = new JPanel(new GridLayout(1, 6));
        JLabel tagLabel = new JLabel("Tag");
        JLabel indexLabel = new JLabel("Index");
        JLabel offsetLabel = new JLabel("Offset");

        tagLabel.setOpaque(true);
        tagLabel.setBackground(Color.GREEN);
        indexLabel.setOpaque(true);
        indexLabel.setBackground(Color.GREEN);
        offsetLabel.setOpaque(true);
        offsetLabel.setBackground(Color.GREEN);

        tagValueLabel = new JLabel("");
        indexValueLabel = new JLabel("");
        offsetValueLabel = new JLabel("");

        tagValueLabel.setBackground(Color.GREEN);
        indexValueLabel.setBackground(Color.GREEN);
        offsetValueLabel.setBackground(Color.GREEN);

        tagValueLabel.setOpaque(true);
        indexValueLabel.setOpaque(true);
        offsetValueLabel.setOpaque(true);

        tagPanel.add(tagLabel);
        tagPanel.add(tagValueLabel);
        tagPanel.add(indexLabel);
        tagPanel.add(indexValueLabel);
        tagPanel.add(offsetLabel);
        tagPanel.add(offsetValueLabel);

        add(tagPanel, gbc);

        // Cache hits and misses Panel
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        JPanel cachePanel = new JPanel(new GridLayout(1, 6));

        cacheHitsLabel = new JLabel("Cache Hits:");
        cacheHitsLabel.setOpaque(true);
        cacheHitsLabel.setBackground(Color.CYAN);

        cacheMissesLabel = new JLabel("Cache Misses:");
        cacheMissesLabel.setOpaque(true);
        cacheMissesLabel.setBackground(Color.CYAN);

        cacheHitsNumberLabel = new JLabel("0");
        cacheHitsNumberLabel.setOpaque(true);
        cacheHitsNumberLabel.setBackground(Color.CYAN);

        cacheMissesNumberLabel = new JLabel("0");
        cacheMissesNumberLabel.setOpaque(true);
        cacheMissesNumberLabel.setBackground(Color.CYAN);

        JLabel space = new JLabel(" ");
        space.setOpaque(true);
        space.setBackground(Color.CYAN);

        cachePanel.add(cacheHitsLabel);
        cachePanel.add(cacheHitsNumberLabel);
        cachePanel.add(cacheMissesLabel);
        cachePanel.add(cacheMissesNumberLabel);
        cachePanel.add(space);

        add(cachePanel, gbc);
    }

    public void updateAddress(Address address) {
        String tag = address.getTag();
        String index = address.getIndex();
        String offset = address.getOffset();

        tagValueLabel.setText(tag);
        indexValueLabel.setText(index);
        offsetValueLabel.setText(offset);
    }

    public void updateLabels(int hitCount, int missCount) {
        cacheHitsNumberLabel.setText(Integer.toString(hitCount));
        cacheMissesNumberLabel.setText(Integer.toString(missCount));
    }

    // Getters for components
    public JButton getReadButton() {
        return readButton;
    }

    public JButton getWriteButton() {
        return writeButton;
    }

    public JButton getFlushButton() {
        return flushButton;
    }

    public JTextField getAddressField1() {
        return addressField1;
    }

    public JTextField getAddressField2() {
        return addressField2;
    }

    public JTextField getByteField() {
        return byteField;
    }

    public JLabel getCacheHitsLabel() {
        return cacheHitsLabel;
    }

    public JLabel getCacheMissesLabel() {
        return cacheMissesLabel;
    }

    public JLabel getCacheHitsNumberLabel() {
        return cacheHitsNumberLabel;
    }

    public JLabel getCacheMissesNumberLabel() {
        return cacheMissesNumberLabel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Memory Access Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MemoryAccessPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
