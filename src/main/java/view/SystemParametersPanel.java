package view;

import javax.swing.*;
import java.awt.*;

public class SystemParametersPanel extends JPanel {

    private JComboBox<Integer> addressWidthCombo;
    private JComboBox<Integer> cacheSizeCombo;
    private JRadioButton block2, block4, block8;
    private ButtonGroup blockSizeGroup;
    private JRadioButton assoc1, assoc2, assoc4, assocn;
    private ButtonGroup associativityGroup;
    private JComboBox<String> writeHitCombo;
    private JComboBox<String> replacementCombo;
    private JCheckBox explainCheckBox;
    private JButton nextButton;
    private JTextArea logArea;

    public SystemParametersPanel() {
        setLayout(new GridBagLayout());
        initPanel();  // Call the initPanel method to initialize components and layout
    }

    private void initPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Address Width
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Address width:"), gbc);

        addressWidthCombo = new JComboBox<>(new Integer[]{6, 8, 10, 12});
        gbc.gridx = 1;
        add(addressWidthCombo, gbc);

        // Cache Size
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cache size:"), gbc);

        cacheSizeCombo = new JComboBox<>(new Integer[]{16, 32, 64, 128});
        gbc.gridx = 1;
        add(cacheSizeCombo, gbc);

        // Block Size
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Block size:"), gbc);

        blockSizeGroup = new ButtonGroup();
        block2 = new JRadioButton("2");
        block4 = new JRadioButton("4");
        block8 = new JRadioButton("8");
        blockSizeGroup.add(block2);
        blockSizeGroup.add(block4);
        blockSizeGroup.add(block8);
        block2.setSelected(true);

        JPanel blockSizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        blockSizePanel.add(block2);
        blockSizePanel.add(block4);
        blockSizePanel.add(block8);
        gbc.gridx = 1;
        add(blockSizePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Associativity:"), gbc);

        associativityGroup = new ButtonGroup();
        assoc1 = new JRadioButton("1");
        assoc2 = new JRadioButton("2");
        assoc4 = new JRadioButton("4");
        assocn = new JRadioButton("n");

        associativityGroup.add(assoc1);
        associativityGroup.add(assoc2);
        associativityGroup.add(assoc4);
        associativityGroup.add(assocn);
        assoc1.setSelected(true);

        JPanel associativityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        associativityPanel.add(assoc1);
        associativityPanel.add(assoc2);
        associativityPanel.add(assoc4);
        associativityPanel.add(assocn);
        gbc.gridx = 1;
        add(associativityPanel, gbc);

        // Write Hit Policy
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Write Hit:"), gbc);

        writeHitCombo = new JComboBox<>(new String[]{"Write back", "Write through"});
        gbc.gridx = 1;
        add(writeHitCombo, gbc);

        // Replacement Policy
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Replacement:"), gbc);

        replacementCombo = new JComboBox<>(new String[]{"Least Recently Used", "First In First Out", "Random"});
        gbc.gridx = 1;
        add(replacementCombo, gbc);

        // Explain Checkbox
        explainCheckBox = new JCheckBox("Explain");
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(explainCheckBox, gbc);

        // Next Button
        nextButton = new JButton("Start");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(nextButton, gbc);

        // Log Area at the Bottom
        logArea = new JTextArea();
        logArea.setEditable(false);  // Make it read-only for logging purposes
        logArea.setLineWrap(true);  // Enable line wrapping
        logArea.setWrapStyleWord(true);  // Wrap at word boundaries

        JScrollPane logScrollPane = new JScrollPane(logArea);  // Add scroll pane for log area
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;  // Make it expand vertically
        gbc.fill = GridBagConstraints.BOTH;  // Allow resizing in both directions
        add(logScrollPane, gbc);
    }

    public int getSelectedBlockSize() {
        if (block2.isSelected()) {
            return 2;
        } else if (block4.isSelected()) {
            return 4;
        } else if (block8.isSelected()) {
            return 8;
        }
        return -1;
    }

    public int getSelectedAssociativity() {
        if (assoc1.isSelected()) {
            return 1;
        } else if (assoc2.isSelected()) {
            return 2;
        } else if (assoc4.isSelected()) {
            return 4;
        }

        return 0;
    }

    public JComboBox<Integer> getAddressWidthCombo() {
        return addressWidthCombo;
    }

    public JComboBox<Integer> getCacheSizeCombo() {
        return cacheSizeCombo;
    }

    public ButtonGroup getBlockSizeGroup() {
        return blockSizeGroup;
    }

    public ButtonGroup getAssociativityGroup() {
        return associativityGroup;
    }

    public JComboBox<String> getWriteHitCombo() {
        return writeHitCombo;
    }

    public JComboBox<String> getReplacementCombo() {
        return replacementCombo;
    }

    public JCheckBox getExplainCheckBox() {
        return explainCheckBox;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("System Parameters Panel");
        frame.add(new SystemParametersPanel());
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
