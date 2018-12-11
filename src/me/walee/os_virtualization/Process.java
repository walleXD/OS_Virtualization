package me.walee.os_virtualization;

public class Process {
    private PCB pcb;

    public PCB getPcb() {
        return pcb;
    }

    Process(PCB pcb) {
        this.pcb = pcb;
    }
}
