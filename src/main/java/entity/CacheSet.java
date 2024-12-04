package entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CacheSet {
    private List<CacheLine> lines;
    private int index;
    private boolean full = false;
    private Queue<CacheLine> LRU;
    private Queue<CacheLine> FIFO;

    public CacheSet(int associativity, int index) {
        this.FIFO = new LinkedList<>();
        this.LRU = new LinkedList<>();

        this.lines = new ArrayList<>();
        this.index = index;

        for (int i = 0; i < associativity; i++) {
            lines.add(new CacheLine());
        }
    }

    public void writeBlock(Address address, List<MemoryCell> block) {
        for (CacheLine line : lines) {
            if (!line.isValidBit()) {
                line.writeBlock(address, block);
                updateFifo(line);
                updateLRU(line);
                updateFull();
                return;
            }
        }

        this.full = true;
    }

    public List<MemoryCell> ejectLine(CacheLine line) {
        List<MemoryCell> ejectedContent = line.ejectLine();
        updateFull();
        return ejectedContent;
    }

    private void updateFull() {
        for (CacheLine line : lines) {
            if (!line.isValidBit()) {
                this.full = false;
                return;
            }
        }
        this.full = true;
    }

    public void updateLRU(CacheLine line) {
        LRU.remove(line);
        LRU.add(line);
    }

    public void updateFifo(CacheLine line) {
        FIFO.add(line);
    }

    public List<CacheLine> getLines() {
        return lines;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFull() {
        return full;
    }

    public CacheLine getLRU() {
        return LRU.poll();
    }

    public Queue<CacheLine> getFIFO() {
        return FIFO;
    }
}
