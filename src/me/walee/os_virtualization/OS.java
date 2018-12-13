package me.walee.os_virtualization;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.lang.RuntimeException;

public class OS {
    private Integer lastPID = 0;

    public Memory getRam() {
        return ram;
    }

    private Memory ram;

    public Map<Integer, PCB> getProcessTable() {
        return processTable;
    }

    private Map<Integer, PCB> processTable = new HashMap<>();

    public Disk getDisk(int id) {
        for(Disk d : allDisks) {
            if (d.getDiskId() == id) return d;
        }

        throw new RuntimeException("No Disk with id " + id + " found.");
    };

    private List<Disk> allDisks = new ArrayList<>();
    private List<Integer> readyQueue = new ArrayList<>();


    public OS(Integer ramSize, Integer disk) {
        this.ram = new Memory(ramSize);
        createAllDisks(disk);
        // TODO: Add CPU with active process slot
        // TODO: Add Ready Queue
        // TODO: Add I/O Queue in each disk
    }

    public Process createProcess(Integer priority, Integer memSize) {
        PCB newPCB = new PCB(getNewPID(), "NEW", memSize, priority);
        processTable.put(newPCB.getPid(), newPCB);
        Process newProcess = new Process(newPCB);

        addProcessToMem(newProcess);
        addProcessToReadyQueue(newProcess.getPcb().getPid());

        newProcess.getPcb().setState("READY");

        return newProcess;
    }

    public void addProcessToReadyQueue(int pid) {
        readyQueue.add(pid);
        reevalauteReadyQueue();
    }

    private void moveProcessFromReadyQueueToDiskQueue(int pid, int diskId) {}

    private void reevalauteReadyQueue() {}

    private void addProcessToMem(Process process) {
        ram.addProcess(process);
    }

    private Integer getNewPID() {
        lastPID += 1;
        return lastPID;
    }

    private void createAllDisks(Integer diskCount) {
        for(int i = 0; i<diskCount; i++) {
            allDisks.add(new Disk(i));
        }
    }
}
