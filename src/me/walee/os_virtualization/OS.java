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

        newProcess.getPcb().setState("READY");
        addProcessToReadyQueue(newProcess.getPcb().getPid());

        reevalauteReadyQueue();

        return newProcess;
    }

    public void addProcessToReadyQueue(int pid) {
        int activePid = cpu.getActivePid();

        if(activePid >= 0) {
            readyQueue.add(pid);
            setProcessState(pid, "READY");
            reevalauteReadyQueue();
        } else {
            cpu.setActivePid(pid);
            setProcessState(pid, "RUNNING");
        }
    }

    public void terminateCurrentProccess() {
        int pid = cpu.getActivePid();
        PCB activeProcess = processTable.get(pid);
        activeProcess.setState("TERMINATED");

        cpu.setActivePid(-1);

        reevalauteReadyQueue();
    }

    public void readFileFromDisk(Integer id, String filename) {
        Disk currDisk = getDisk(id);
        currDisk.addProcess(cpu.getActivePid(), filename);

        setProcessState(cpu.getActivePid(), "WAITING");

        cpu.setActivePid(-1);

        reevalauteReadyQueue();
    }

    public List<Disk> getAllDisks() {
        return allDisks;
    }

    public void completeDiskUsage(int i) {
        int pid = getDisk(i).completeRunningProcess();
        addProcessToReadyQueue(pid);
    }

    private Integer lastPID = 0;
    private CPU cpu = new CPU();
    private Map<Integer, PCB> processTable = new HashMap<>();
    private Memory ram;

    private List<Disk> allDisks = new ArrayList<>();
    private List<Integer> readyQueue = new ArrayList<>();

    private void reevalauteReadyQueue() {
        if (readyQueue.size() == 0) return;
        else if (cpu.getActivePid() == -1) {
            PCB highPriorityPCB = readyQueue
                    .stream()
                    .map(pid -> processTable.get(pid))
                    .reduce((PCB prev, PCB next) ->
                            prev.getPriority() > next.getPriority()
                                ? prev
                                : next
                    ).get();

            setNewActiveProcess(highPriorityPCB.getPid());
        }
        else {
            int oldaActivePid = cpu.getActivePid();
            int activeProcessPriority = processTable.get(oldaActivePid).getPriority();
            int newActivePid = -1;

            for(int p: readyQueue) {
                if (processTable.get(p).getPriority() > activeProcessPriority) newActivePid = p;
            }

            if(newActivePid >= 0 && newActivePid != oldaActivePid) setNewActiveProcess(newActivePid, oldaActivePid);
        }
    }

    private void setNewActiveProcess(Integer newActivePid) {
        cpu.setActivePid(newActivePid);
        readyQueue.remove(readyQueue.indexOf(newActivePid));
        setProcessState(newActivePid, "RUNNING");
    }

    private void setNewActiveProcess(Integer newActivePid, Integer oldActivePid) {
        setNewActiveProcess(newActivePid);

        readyQueue.add(oldActivePid);
        setProcessState(oldActivePid, "READY");
    }

    private void setProcessState(int pid, String state) {
        processTable.get(pid).setState(state);
    }
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
