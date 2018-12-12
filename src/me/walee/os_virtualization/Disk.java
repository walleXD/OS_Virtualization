package me.walee.os_virtualization;

import java.util.ArrayList;
import java.util.List;

// All I/O-queues are FCFS.
public class Disk {
    private List<Integer> ioQueue = new ArrayList<Integer>();

    public Integer getDiskId() {
        return diskId;
    }

    private Integer diskId;

    public Disk(Integer id) {
        diskId = id;
    }

}
