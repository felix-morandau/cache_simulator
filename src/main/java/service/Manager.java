package service;

import entity.*;
import util.ReplacementPolicy;
import util.WritePolicy;
import util.WriteThrough;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Manager {
    private CacheMemory cache;
    private MainMemory mainMemory;
    private ReplacementPolicy replacementPolicy;
    private WritePolicy writePolicy;

    private CacheMemoryPanelManager cachePanelManager;
    private MainMemoryPanelManager memoryPanelManager;
    private SimulationMessagesPanelManager messagesPanelManager;

    private Queue<Address> addressesQueue = new LinkedList<>();

    public Manager(SimulationMessagesPanelManager messagesPanelManager, MainMemoryPanelManager memoryPanelManager, CacheMemoryPanelManager cachePanelManager) {
        this.messagesPanelManager = messagesPanelManager;
        this.memoryPanelManager = memoryPanelManager;
        this.cachePanelManager = cachePanelManager;
    }

    public void initializeSimulation() {
        this.cache = new CacheMemory();
        cachePanelManager.populateTable(cache);

        this.mainMemory = new MainMemory(SystemParameters.getInstance().getMainMemorySize());
        setMainMemory();

        this.replacementPolicy = SystemParameters.getInstance().getReplacementPolicy();
        this.writePolicy = SystemParameters.getInstance().getWritePolicy();

        this.messagesPanelManager.clear();
    }

    public CacheResult writeOperation(String hexAddress, MemoryCell data) {
        Address address = getAddressFromHexString(hexAddress);
        addressesQueue.add(address);

        CacheResult result = (cache.read(address) == null) ? CacheResult.MISS : CacheResult.HIT;

        if (result == CacheResult.MISS || writePolicy instanceof WriteThrough) {
            mainMemory.write(address, data);
            addressesQueue.remove();
            memoryPanelManager.colorCell(address);
        } else {
            writePolicy.write(mainMemory, cache, address, data);
        }

        messagesPanelManager.addMessage(address, result, replacementPolicy, writePolicy, data.getHexData());

        return result;
    }

    public CacheResult readOperation(String hexAddress) {
        List<MemoryCell> replacedContent = null;

        Address address = getAddressFromHexString(hexAddress);
        CacheResult result = CacheResult.HIT;
        CacheLine lineRetrieved = cache.read(address);

        if (lineRetrieved == null) {
            result = CacheResult.MISS;
            List<MemoryCell> mainMemoryBlock = mainMemory.read(address);

            memoryPanelManager.colorCell(address);

            if (!cache.write(address, mainMemoryBlock)) {
                replacedContent = replacementPolicy.replace(cache, address, mainMemoryBlock);
            }
        }

        lineRetrieved = cache.read(address);
        if (lineRetrieved != null) {
            cachePanelManager.colorLine(lineRetrieved);
        }

        if (replacedContent != null) {
            Address writeAddress = addressesQueue.remove();
            mainMemory.writeBlock(writeAddress, replacedContent);
            setMainMemory();
            memoryPanelManager.colorCell(writeAddress);
        }

        messagesPanelManager.addMessage(address, result, replacementPolicy);

        return result;
    }

    public void setCachePanel() {
        cachePanelManager.populateTable(cache);
    }

    public void setMainMemory() {
        memoryPanelManager.populateTable(mainMemory);
    }

    public Address getAddressFromHexString(String hexAddress) {
        int decimal = Integer.parseInt(hexAddress, 16);
        String binaryAddress = Integer.toBinaryString(decimal);

        return new Address(AddressTemplate.getInstance(), binaryAddress);
    }
}
