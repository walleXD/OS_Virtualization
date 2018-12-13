package me.walee.os_virtualization;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.lang.RuntimeException;

public class OS {
    public Map<Integer, PCB> getProcessTable() {
        return processTable;
    }

    public Memory getRam() {
        return ram;
    }

    public Disk getDisk(int id) {
        for(Disk d : allDisks) {
            if (d.getDiskId() == id) return d;
        }

        throw new RuntimeException("No Disk with id " + id + " found.");
    }

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

    public void terminateCurrentProccess() {
        int pid = cpu.getActivePid();
        PCB activeProcess = processTable.get(pid);
        activeProcess.setState("TERMINATED");
        reevalauteReadyQueue();
    }

    private Integer lastPID = 0;
    private CPU cpu = new CPU();
    private Map<Integer, PCB> processTable = new HashMap<>();
    private Memory ram;
    private List<Disk> allDisks = new ArrayList<>();
    private List<Integer> readyQueue = new ArrayList<>();

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
