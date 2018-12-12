package me.walee.os_virtualization;

import java.util.HashMap;
import java.util.Map;

public class OS {
    private Integer diskCount;
    private Integer lastPID = 0;

    public Memory getRam() {
        return ram;
    }

    private Memory ram;

    public Map<Integer, PCB> getProcessTable() {
        return processTable;
    }

    private Map<Integer, PCB> processTable = new HashMap<>();

    public OS(Integer ramSize, Integer disk) {
        this.diskCount = disk;
        this.ram = new Memory(ramSize);
        // TODO: Add disk creation
        // TODO: Add CPU with active process slot
        // TODO: Add Ready Queue
        // TODO: Add I/O Queue in each disk
    }

    public Process createProcess(Integer priority, Integer memSize) {
        PCB newPCB = new PCB(getNewPID(), "NEW", memSize, priority);
        processTable.put(newPCB.getPid(), newPCB);
        Process newProcess = new Process(newPCB);

        addProcessToMem(newProcess);
        addProcessToReadyQueue(newProcess);

        newProcess.getPcb().setState("READY");

        return newProcess;
    }

    private void addProcessToReadyQueue(Process process) {}

    private void reevalauteReadyQueue() {}

    private void addProcessToMem(Process process) {
        ram.addProcess(process);
    }

    private Integer getNewPID() {
        lastPID += 1;
        return lastPID;
    }
}
