package util;

import entity.*;

import java.util.List;

public class LRU implements ReplacementPolicy{
    public List<MemoryCell> replace(CacheMemory cache, Address address, List<MemoryCell> block) {
        CacheSet correspondingSet = cache.getCorrespondingSet(address);
        CacheLine lineToBeEjected = correspondingSet.getLRU();

        List<MemoryCell> contents = cache.deleteEntry(address, lineToBeEjected);
        cache.write(address, block);

        return contents;
    }
}
