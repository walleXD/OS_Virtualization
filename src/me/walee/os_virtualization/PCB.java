package me.walee.os_virtualization;

public class PCB {
    private Integer pid;
    private String state;
    private Integer ram;
    private Integer priority;

    public Integer getPid() {
        return pid;
    }

    public String getState() {
        return state;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PCB(Integer pid, String state, Integer ram, Integer priority) {
        this.pid = pid;
        this.state = state;
        this.ram = ram;
        this.priority = priority;
    }
}
