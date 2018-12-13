package me.walee.os_virtualization;

// Scheduling: your program should implement preemptive priority CPU-scheduling. Higher numbers mean higher priority.
public class CPU {
    public void setActivePid(int activePid) {
        this.activePid = activePid;
    }

    public int getActivePid() {
        return activePid;
    }

    private int activePid = -1;
}
