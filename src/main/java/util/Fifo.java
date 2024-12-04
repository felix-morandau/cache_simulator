package util;

import entity.*;

import java.util.List;

public class Fifo implements ReplacementPolicy{
    public List<MemoryCell> replace(CacheMemory cache, Address address, List<MemoryCell> block) {
        CacheSet correspondingSet = cache.getCorrespondingSet(address);
        CacheLine lineToBeEjected = correspondingSet.getFIFO().poll();

        List <MemoryCell> contents = cache.deleteEntry(address, lineToBeEjected);
        cache.write(address, block);

        return contents;
    }
}
