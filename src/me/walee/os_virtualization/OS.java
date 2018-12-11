package me.walee.os_virtualization;

import java.util.Map;

public class OS {
    private Integer ramSize;
    private Integer diskCount;
    private Integer lastPID = 0;
    private Map<Integer, PCB> processTable;

    public OS(Integer ram, Integer disk) {
        this.ramSize = ram;
        this.diskCount = disk;
        // TODO: Add memory creation
        // TODO: Add disk creation
        // TODO: Add CPU with active process slot
        // TODO: Add Ready Queue
        // TODO: Add I/O Queue in each disk
    }

    public Process createProcess(Integer priority, Integer memSize) {
        PCB newPCB = new PCB(getNewPID(), "NEW", memSize, priority);
        this.processTable.put(newPCB.getPid(), newPCB);
        Process newProcess = new Process(newPCB);

        addProcessToMem(newProcess);
        addProcessToReadyQueue(newProcess);

        newProcess.getPcb().setState("READY");

        return newProcess;
    }

    private void addProcessToReadyQueue(Process process) {}

    private void reevalauteReadyQueue() {}

    private void addProcessToMem(Process process) {}

    private Integer getNewPID() {
        lastPID += 1;
        return lastPID;
    }
}
