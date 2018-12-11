package me.walee.os_virtualization;

import java.util.List;

public class OS {
    private Integer ramSize;
    private Integer diskCount;
    private Integer lastPID = 0;

    public OS(Integer ram, Integer disk) {
        ramSize = ram;
        diskCount = disk;
        // TODO: Add memory creation
        // TODO: Add disk creation
        // TODO: Add CPU with active process slot
        // TODO: Add Ready Queue
        // TODO: Add I/O Queue in each disk
    }

    public Process createProcess(Integer priority, Integer memSize) {
        Process newProcess = new Process(priority, memSize, getNewPID());
        // TODO: add process to memory
        // TODO: add all other OS pipeline

        return newProcess;
    }

    private Integer getNewPID() {
        lastPID += 1;
        return lastPID;
    }
}
