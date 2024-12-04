package util;

import entity.Address;
import entity.CacheMemory;
import entity.MainMemory;
import entity.MemoryCell;

public class WriteThrough implements WritePolicy {

    @Override
    public void write(MainMemory mainMemory, CacheMemory cache, Address address, MemoryCell data) {

    }
}
