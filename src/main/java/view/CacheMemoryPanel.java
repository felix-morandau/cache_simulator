package view;

import entity.*;
import util.Fifo;
import util.WriteThrough;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class CacheMemoryPanel extends JPanel {

    private JTable cacheTable;
    private DefaultTableModel tableModel;
    private CacheLine lineColored;

    public CacheMemoryPanel() {
        setLayout(new BorderLayout());
        initPanel();
    }

    private void initPanel() {
        String[] columnNames = {"Set", "V", "D", "T", "Cache Data"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        cacheTable = new JTable(tableModel);

        TableColumnModel columnModel = cacheTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // "Set" column
        columnModel.getColumn(1).setPreferredWidth(30);  // "V" column
        columnModel.getColumn(2).setPreferredWidth(30);  // "D" column
        columnModel.getColumn(3).setPreferredWidth(150);  // "T" column
        columnModel.getColumn(4).setPreferredWidth(200); // "Cache Data" column

        JScrollPane scrollPane = new JScrollPane(cacheTable);
        add(new JLabel("Cache Memory", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void populateData(CacheMemory cache) {
        tableModel.setRowCount(0);

        List<CacheSet> sets = cache.getSets();
        for (int i = 0; i < sets.size(); i++) {
            CacheSet set = sets.get(i);

            for (CacheLine line : set.getLines()) {
                String validBit = line.isValidBit() ? "1" : "0";
                String dirtyBit = line.isDirtyBit() ? "1" : "0";
                String tag = line.getTag();

                StringBuilder dataString = new StringBuilder();
                for (MemoryCell data : line.getBlock()) {
                    dataString.append(data.getHexData()).append(" ");
                }

                tableModel.addRow(new Object[]{
                        "Set " + i,       // Set number
                        validBit,         // Valid bit
                        dirtyBit,         // Dirty bit
                        tag,              // Tag
                        dataString.toString().trim() // Cache Data as a string
                });

                // Check if this is the line to be colored
                int rowIndex = tableModel.getRowCount() - 1; // Current row index
                if (line.equals(lineColored)) {
                    cacheTable.setRowSelectionInterval(rowIndex, rowIndex); // Select the row
                    cacheTable.setSelectionBackground(Color.YELLOW); // Highlight the row
                    lineColored = null;
                }
            }
        }
    }


    public void colorLine(CacheLine cacheLine) {
        lineColored = cacheLine;
    }

    public JTable getCacheTable() {
        return cacheTable;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cache Memory View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SystemParameters.getInstance(10, 64, 2, 2, new WriteThrough(), new Fifo());

        CacheMemoryPanel cachePanel = new CacheMemoryPanel();
        frame.add(cachePanel);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}
