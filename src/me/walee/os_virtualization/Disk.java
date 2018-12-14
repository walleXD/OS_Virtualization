package me.walee.os_virtualization;

import java.util.TreeMap;

// All I/O-queues are FCFS.
public class Disk {
    public Integer getDiskId() {
        return diskId;
    }

    public Disk(Integer id) {
        diskId = id;
    }

    public void addProcess(int pid, String filename) {
        if (activePid > 0) ioQueue.put(pid, filename);
        else setNewActiveProcess(pid, filename);
    }

    public Integer completeRunningProcess() {
        int activePid = this.activePid;

        if(ioQueue.size() > 0) {
            setNewActiveProcess();
        } else {
            this.activePid = -1;
        }

        return activePid;
    }

    public void setNewActiveProcess(){
        this.activePid = ioQueue.firstEntry().getKey();
        this.file = ioQueue.firstEntry().getValue();

        ioQueue.remove(this.activePid);
    }

    public void setNewActiveProcess(int i, String filename) {
        this.activePid = i;
        this.file = filename;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getActivePid() {
        return activePid;
    }

    public void setActivePid(Integer activePid) {
        this.activePid = activePid;
    }

    public TreeMap<Integer, String> getIoQueue() {
        return ioQueue;
    }

    private Integer diskId;
    private TreeMap<Integer, String> ioQueue = new TreeMap<>();
    private String file;
    private Integer activePid = -1;
}
