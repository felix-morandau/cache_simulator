package entity;

import util.Fifo;
import util.ReplacementPolicy;
import util.WritePolicy;
import util.WriteThrough;

public class SystemParameters {
    private int addressWidth;
    private int mainMemorySize;
    private int cacheSize;
    private int blockSize;
    private int associativity;
    private WritePolicy writePolicy;
    private ReplacementPolicy replacementPolicy;
    private int nrOfSets;
    private AddressTemplate addressTemplate;
    private static SystemParameters instance;

    private SystemParameters(int addressWidth, int cacheSize, int blockSize, int associativity, WritePolicy writePolicy, ReplacementPolicy replacementPolicy) {
        this.addressWidth = addressWidth;
        this.mainMemorySize = (int) Math.pow(2, addressWidth);
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.associativity = associativity;
        this.writePolicy = writePolicy;
        this.replacementPolicy = replacementPolicy;
        this.nrOfSets = calculateNrOfSets();
        this.addressTemplate = calculateAddressTemplate();
    }

    public static SystemParameters getInstance(int addressWidth, int cacheSize, int blockSize, int associativity, WritePolicy writePolicy, ReplacementPolicy replacementPolicy) {
        if (instance == null) {
            instance = new SystemParameters(addressWidth, cacheSize, blockSize, associativity, writePolicy, replacementPolicy);
        }

        return instance;
    }

    public void setParameters(int addressWidth, int cacheSize, int blockSize, int associativity, WritePolicy writePolicy, ReplacementPolicy replacementPolicy) {
        this.addressWidth = addressWidth;
        this.mainMemorySize = (int) Math.pow(2, addressWidth);
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.associativity = associativity;
        this.writePolicy = writePolicy;
        this.replacementPolicy = replacementPolicy;
        this.nrOfSets = calculateNrOfSets();
        this.addressTemplate = calculateAddressTemplate();
    }

    public static SystemParameters getInstance() {
        return instance;
    }

    private int calculateNrOfSets() {
        if (this.associativity == 0) {
            this.associativity = this.cacheSize / this.blockSize;
            return 1;
        }

        return this.cacheSize / this.blockSize / this.associativity;
    }

    private AddressTemplate calculateAddressTemplate() {
        int offsetWidth = (int) (Math.log(this.blockSize) / Math.log(2));
        int indexWidth = (int) (Math.log(this.nrOfSets) / Math.log(2));
        int tagWidth = this.addressWidth - offsetWidth - indexWidth;

        return AddressTemplate.getInstance(tagWidth, indexWidth, offsetWidth);
    }

    public int getAddressWidth() {
        return addressWidth;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getAssociativity() {
        return associativity;
    }

    public WritePolicy getWritePolicy() {
        return writePolicy;
    }

    public ReplacementPolicy getReplacementPolicy() {
        return replacementPolicy;
    }

    public int getNrOfSets() {
        return nrOfSets;
    }

    public int getMainMemorySize() {
        return mainMemorySize;
    }

    public AddressTemplate getAddressTemplate() {
        return addressTemplate;
    }

    public void setAddressTemplate(AddressTemplate addressTemplate) {
        this.addressTemplate = addressTemplate;
    }

    public String getFirstCalculation() {
        return "There are " + addressWidth + " bits for address\n" +
                "So memory holds " + (int) Math.pow(2, addressWidth) + " bytes";
    }

    public String getSecondCalculation() {

        return "Block size = " + blockSize + " bytes\n" +
                "Cache size = " + cacheSize + " bytes\n" +
                "So " + associativity * nrOfSets + " lines\n" +
                "in " + nrOfSets + " sets\n" +
                "Management bits:\n" + "-Valid bit\n" + "-Dirty bit\n";
    }

    public String getThirdCalculation() {

        return "Offset width:\n" + "= log_2(block_size)\n" + "= " + addressTemplate.getOffsetSize() +
                "\nIndex width:\n" + "= log_2(nr_of_sets)\n" + "= " + addressTemplate.getIndexSize() +
                "\nTag width:\n" + "= address_width - index_width - offset_width = " + addressTemplate.getTagSize();
    }
}
