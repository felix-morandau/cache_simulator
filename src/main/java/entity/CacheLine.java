package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CacheLine {
    private boolean validBit;
    private boolean dirtyBit;
    private String tag;
    private List<MemoryCell> block;

    public CacheLine() {
        validBit = false;
        dirtyBit = false;
        tag = "0";
        block = new ArrayList<>();
    }

    public boolean containsData(Address address) {
        return  Objects.equals(address.getTag(), this.tag);
    }

    public void writeBlock(Address address, List<MemoryCell> newBlock) {
        this.tag = address.getTag();
        this.block = newBlock;
        this.validBit = true;
    }

    public List<MemoryCell> ejectLine() {
        List<MemoryCell> replacedBlock = null;
        if (dirtyBit) {
            replacedBlock = block;
        }

        this.validBit = false;
        this.dirtyBit = false;

        return replacedBlock;
    }

    public void setData(List<MemoryCell> data) {
        this.block = data;
    }

    public void setDirty() {
        this.dirtyBit = true;
    }
    public boolean isValidBit() {
        return validBit;
    }

    public boolean isDirtyBit() {
        return dirtyBit;
    }

    public String getTag() {
        return tag;
    }

    public List<MemoryCell> getBlock() {
        return block;
    }
}
