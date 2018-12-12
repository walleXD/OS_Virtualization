package me.walee.os_virtualization;

import java.util.ArrayList;
import java.util.List;

// Memory: your program should simulate contiguous memory management with “best fit” approach.
public class Memory {
     class MemoryBlock {
        private Integer blockSize;

         public Integer getContent() {
             return content;
         }

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

    public List<MemoryBlock> getRawRam() {
        return ram;
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
//        TODO: Handle case for when there isn't any block available
        int bestMemBlockIndex = 0;

        for(int i = 0; i < ram.size(); i++) {
            MemoryBlock b = ram.get(i);
            if (
                    b.size() > block.size()
                            && b.isEmpty()
                            && b.size() > ram.get(bestMemBlockIndex).size()) {
                bestMemBlockIndex = i;
            }
        }

        int emptyBlockSize = ram.get(bestMemBlockIndex).size() - block.size();
        ram.remove(bestMemBlockIndex);
        if (emptyBlockSize > 0) {
            ArrayList<MemoryBlock> newBlocks = new ArrayList<>();
            newBlocks.add(block);
            newBlocks.add(new MemoryBlock(emptyBlockSize));
            ram.addAll(bestMemBlockIndex, newBlocks);
        } else {
            ram.add(bestMemBlockIndex, block);
        }
    }
}
