package me.walee.os_virtualization;

public class Process {
    public Integer getRamSize() {
        return ramSize;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getPid() {
        return pid;
    }

    private Integer ramSize;
    private Integer priority;
    private Integer pid;

    Process(Integer ram, Integer p, Integer PID) {
        ramSize = ram;
        priority = p;
        pid = PID;
    }
}
