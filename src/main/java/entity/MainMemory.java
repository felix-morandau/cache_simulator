package entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainMemory {
    private Map<Address, MemoryCell> memory;

    public MainMemory(int size) {
        memory = new LinkedHashMap<>();
        loadSampleData(size);
    }

    public void loadSampleData(int size) {
        clear();

        for (int i = 0; i < size; i++) {
            Address address = new Address(AddressTemplate.getInstance(), Integer.toBinaryString(i));
            MemoryCell cell = new MemoryCell("00");

            String hexData = String.format("%02x", (int) (Math.random() * 256));
            cell.setHexData(hexData);

            memory.put(address, cell);
        }
    }

    public Map<Address, MemoryCell> getMemoryData() {
        return memory;
    }

    public List<MemoryCell> read(Address address) {
        int maxOffset = (int) Math.pow(2, AddressTemplate.getInstance().getOffsetSize());
        List<MemoryCell> block = new ArrayList<>();

        int baseLength = address.getAddress().length() - AddressTemplate.getInstance().getOffsetSize();
        String blockAddressBinary = address.getAddress().substring(0, baseLength);

        for (int i = 0; i < maxOffset; i++) {

            String fullAddressBinary = blockAddressBinary + String.format("%" + AddressTemplate.getInstance().getOffsetSize() + "s", Integer.toBinaryString(i)).replace(' ', '0');
            Address fullAddress = new Address(AddressTemplate.getInstance(), fullAddressBinary);
            MemoryCell cell = memory.getOrDefault(fullAddress, new MemoryCell("00"));
            block.add(cell);
        }

        return block;
    }

    public void write(Address address, MemoryCell data) {

        memory.put(address, data);
    }

    public void writeBlock(Address address, List<MemoryCell> data) {
        AddressTemplate template = AddressTemplate.getInstance();
        int offsetSize = template.getOffsetSize();

        int baseLength = address.getAddress().length() - offsetSize;
        String blockAddressBinary = address.getAddress().substring(0, baseLength);

        for (int i = 0; i < data.size(); i++) {
            String offsetBinary = String.format("%" + offsetSize + "s", Integer.toBinaryString(i)).replace(' ', '0');
            String fullAddressBinary = blockAddressBinary + offsetBinary;

            Address fullAddress = new Address(template, fullAddressBinary);
            memory.put(fullAddress, data.get(i));

            System.out.println("Writing to Address: " + fullAddressBinary + " Data: " + data.get(i).getHexData());
        }
    }



    public void clear() {
        memory.clear();
    }
}
