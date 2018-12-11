package me.walee.os_virtualization;

import java.util.List;

// Memory: your program should simulate contiguous memory management with “best fit” approach.
public class Memory {
    private class MemoryBlock {
        Integer blockSize = 0;
        Integer content;

        MemoryBlock(Integer memSize) {
            blockSize = memSize;
        }
    }

    private List<MemoryBlock> ram;
    private Integer memSize;

    public Memory(Integer memSize) {
        this.memSize = memSize;
        ram.add(new MemoryBlock(this.memSize));
    }
}
