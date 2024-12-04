package view;

import entity.Address;
import entity.AddressTemplate;
import entity.MainMemory;
import entity.MemoryCell;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainMemoryPanel extends JPanel {

    private JTable memoryTable;
    private DefaultTableModel tableModel;
    private Set<MemoryCell> coloredCells = new HashSet<>();

    public MainMemoryPanel() {
        setLayout(new BorderLayout());
        initPanel();
    }

    private void initPanel() {
        String[] columnNames = {"Address", "+0", "+1", "+2", "+3", "+4", "+5", "+6", "+7"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        memoryTable = new JTable(tableModel);
        memoryTable.setFont(new Font("Monospaced", Font.PLAIN, 14));
        memoryTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(memoryTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(new JLabel("Physical Memory", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void populateTableData(MainMemory memory) {
        clearTable();

        Object[] rowData = new Object[9]; // Row data template with 9 columns (address + 8 bytes)
        String lastAddress = null;
        int byteIndex = 1;

        for (Map.Entry<Address, MemoryCell> entry : memory.getMemoryData().entrySet()) {
            Address address = entry.getKey();
            MemoryCell cell = entry.getValue();

            // Start a new row for every 8-byte aligned address
            if (Integer.parseInt(address.getHexAddress(), 16) % 8 == 0) {
                // Add the completed row to the table model
                if (lastAddress != null) {
                    tableModel.addRow(rowData);
                }
                // Reset row data and initialize for new address
                rowData = new Object[9];
                lastAddress = address.getHexAddress();
                rowData[0] = lastAddress; // Set address in the first column
                byteIndex = 1; // Reset byte index for new row
            }

            // Place cell data into the correct column in the row
            rowData[byteIndex] = cell.getHexData();
            byteIndex++;

            // Add the row to the model if it reaches 8 data cells
            if (byteIndex > 8) {
                tableModel.addRow(rowData);
                lastAddress = null;
                byteIndex = 1;
            }
        }

        if (lastAddress != null) {
            tableModel.addRow(rowData);
        }
    }

    public void colorCell(Address address) {
        SwingUtilities.invokeLater(() -> {
            int decimalAddress = Integer.parseInt(address.getHexAddress(), 16);
            int row = decimalAddress / 8;
            int column = 1 + (decimalAddress % 8);

            if (row < tableModel.getRowCount() && column < tableModel.getColumnCount()) {
                memoryTable.setRowSelectionInterval(row, row);
                memoryTable.setSelectionBackground(Color.GREEN);

                Timer timer = new Timer(2000, _ -> memoryTable.setSelectionBackground(UIManager.getColor("Table.selectionBackground")));
                timer.setRepeats(false);
                timer.start();
            } else {
                System.err.println("Address out of bounds: " + address.getHexAddress());
            }
        });
    }


    private void clearTable() {
        tableModel.setRowCount(0);
    }

    public JTable getMemoryTable() {
        return memoryTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Memory Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddressTemplate.getInstance(4, 3, 3);
        MainMemory memory = new MainMemory(1 << 10); // Initialize with 1 KB of memory

        MainMemoryPanel memoryPanel = new MainMemoryPanel();
        frame.add(memoryPanel);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
