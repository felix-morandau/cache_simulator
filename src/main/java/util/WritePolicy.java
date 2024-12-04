package util;

import entity.Address;
import entity.CacheMemory;
import entity.MainMemory;
import entity.MemoryCell;

import java.util.List;

public interface WritePolicy {
    void write(MainMemory mainMemory, CacheMemory cache, Address address, MemoryCell data);
}
