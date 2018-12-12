package me.walee.os_virtualization;

import java.util.ArrayList;
import java.util.List;

// Memory: your program should simulate contiguous memory management with “best fit” approach.
public class Memory {
    private class MemoryBlock {
        private Integer blockSize;
        private Integer content = null;

        public MemoryBlock(Integer memSize) {
            blockSize = memSize;
        }

        public MemoryBlock(Integer memSize, Integer content) {
            blockSize = memSize;
            this.content = content;
        }

        public Boolean isEmpty() { return content == null; }

        public Integer size() { return blockSize; }
    }

    private List<MemoryBlock> ram = new ArrayList<>();
    private Integer memSize;

    public Memory(Integer memSize) {
        this.memSize = memSize;
        ram.add(new MemoryBlock(this.memSize));
    }

    public void addProcess(Process p) {
        Integer allocatedMemSize = p.getPcb().getRam();
        Integer pid = p.getPcb().getPid();

        MemoryBlock newMemBlock = new MemoryBlock(allocatedMemSize, pid);
        addMemBlockToMem(newMemBlock);
    }

    private void addMemBlockToMem(MemoryBlock block) {
//        TODO: Implement Best Fit algorithm to add process to mem
//        int i = 0;
//        for(MemoryBlock b : ram) {
//            if (b.size() > block.size() && b.isEmpty()) {
//                int emptyBlockSize = b.size() - block.size();
//                if (emptyBlockSize > 0) {
//                    MemoryBlock newEmptyBlock = new MemoryBlock(emptyBlockSize);
//                }
//            }
//            i+=1;
//        }
    }
}
