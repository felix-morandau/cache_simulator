package util;

import entity.*;

import java.util.List;
import java.util.Random;

public class RandomBlock implements ReplacementPolicy{
    public List<MemoryCell> replace(CacheMemory cache, Address address, List<MemoryCell> block) {
        CacheSet correspondingSet = cache.getCorrespondingSet(address);
        CacheLine lineToBeEjected = getRandomLine(correspondingSet);

        List<MemoryCell> contents = cache.deleteEntry(address, lineToBeEjected);
        cache.write(address, block);

        return contents;
    }

    private CacheLine getRandomLine(CacheSet set) {
        Random random = new Random();

        int nrOfLines = set.getLines().size();
        return set.getLines().get(random.nextInt(nrOfLines));
    }
}
