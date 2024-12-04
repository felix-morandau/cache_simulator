package util;

import entity.Address;
import entity.CacheMemory;
import entity.MemoryCell;

import java.util.List;

public interface ReplacementPolicy {
    List<MemoryCell> replace(CacheMemory cache, Address address, List<MemoryCell> block);
}
