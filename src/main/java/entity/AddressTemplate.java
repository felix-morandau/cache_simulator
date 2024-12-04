package entity;

public class AddressTemplate {
    private int tagSize;
    private int indexSize;
    private int offsetSize;
    private int size;
    private static AddressTemplate instance;

    private AddressTemplate(int tagSize, int indexSize, int offsetSize) {
        this.tagSize = tagSize;
        this.indexSize = indexSize;
        this.offsetSize = offsetSize;
        this.size = tagSize + indexSize + offsetSize;
    }

    public static AddressTemplate getInstance(int tagSize, int indexSize, int offsetSize) {
        instance = new AddressTemplate(tagSize, indexSize, offsetSize);

        return getInstance();
    }

    public static AddressTemplate getInstance() {
        return instance;
    }

    public int getTagSize() { return tagSize; }
    public int getIndexSize() { return indexSize; }
    public int getOffsetSize() { return offsetSize; }
    public int getSize() { return  size; }
}

