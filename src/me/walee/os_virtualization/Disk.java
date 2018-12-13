package me.walee.os_virtualization;

import java.util.ArrayList;
import java.util.List;
import java.lang.RuntimeException;

// All I/O-queues are FCFS.
public class Disk {
    private List<Integer> ioQueue = new ArrayList<>();
    private Integer activePid = -1;

    public Integer getDiskId() {
        return diskId;
    }

    private Integer diskId;

    public Disk(Integer id) {
        diskId = id;
    }

    public void addProcess(int pid) {
        if (activePid > 0) ioQueue.add(pid);
        else activePid = pid;
    }

    public Integer completeRunningProcess() {
        int activePid = this.activePid;

        if(ioQueue.size() > 0) {
            this.activePid = ioQueue.get(0);
            ioQueue.remove(0);
        } else {
            this.activePid = -1;
        }

        return activePid;
    }

}
