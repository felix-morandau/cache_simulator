package util;

import entity.*;


import java.util.List;

public class WriteBack implements WritePolicy {

    @Override
    public void write(MainMemory mainMemory, CacheMemory cache, Address address, MemoryCell data) {
        CacheLine retrievedLine = cache.read(address);

        if (retrievedLine != null) {
            List<MemoryCell> memoryBlock = retrievedLine.getBlock();
            int offset = Integer.parseInt(address.getOffset(), 2);

            memoryBlock.set(offset, data);
            retrievedLine.setData(memoryBlock);
            retrievedLine.setDirty();
        } else {
            mainMemory.write(address, data);
        }

    }
}
