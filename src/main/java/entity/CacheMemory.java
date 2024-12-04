package entity;

import java.util.ArrayList;
import java.util.List;

public class CacheMemory {
    private List<CacheSet> sets = new ArrayList<>();

    public CacheMemory() {
        for (int i = 0; i < SystemParameters.getInstance().getNrOfSets(); i++) {
            sets.add(new CacheSet(SystemParameters.getInstance().getAssociativity(), i + 1));
        }
    }

    public CacheLine read(Address address) {

        CacheSet correspondingSet = getCorrespondingSet(address);

        for (CacheLine line : correspondingSet.getLines()) {
            if (line.containsData(address)) {
                correspondingSet.updateLRU(line);
                return line;
            }
        }

        return null;
    }

    public boolean write(Address address, List<MemoryCell> block) {
        CacheSet correspondingSet = getCorrespondingSet(address);

        if (correspondingSet.isFull()) {
            return false;
        }

        correspondingSet.writeBlock(address, block);
        return true;
    }

    public List<MemoryCell> deleteEntry(Address address, CacheLine line) {
        CacheSet correspondingSet = getCorrespondingSet(address);
        return correspondingSet.ejectLine(line);
    }

    public void clear() {

    }

    public CacheSet getCorrespondingSet(Address address) {
        if (AddressTemplate.getInstance().getIndexSize() == 0) {
            return sets.getFirst();
        }

        return sets.get(Integer.parseInt(address.getIndex(), 2));
    }

    public List<CacheSet> getSets() {
        return sets;
    }
}
