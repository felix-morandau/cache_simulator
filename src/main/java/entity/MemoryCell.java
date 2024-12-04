package entity;

public class MemoryCell {
    private String hexData;

    public MemoryCell(String data) {
        int decimal = Integer.parseInt(data, 16);
        this.hexData = Integer.toHexString(decimal);
    }

    public String getHexData() {
        return hexData;
    }

    public void setHexData(String hexData) {
        this.hexData = hexData;
    }

    public String toString() {
        return hexData;
    }
}
