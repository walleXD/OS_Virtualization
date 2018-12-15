# OS Virtualization
## Walee Ahmed
### CSCI340

#### Command to compile & run

```bash
cd src/me/walee/os_virtualization
javac -d build/ *.java
java -classpath "build" me.walee.os_virtualization.Main
```

#### Commands available

```bash
A: Spawn new process - A PRIORITY RAM_SIZE
t: Terminate currently running process
d: Currently running process added to I/O queue from given disk to load given file - d NUMBER FILE_NAME
D: The hard disk #number has finished the work for one process - D NUMBER
Sr: Shows a process currently using the CPU and processes waiting in the ready-queue
Si: Shows what processes are currently using the hard disks and what processes are waiting to use them
Sm: Shows the state of memory
help: Lists all available commands
exit: Exits Virtualization
```

#### ToDos

- [ ] Add java doc for how the program works
- [ ] Add better error handling for wrong command inputs
- [ ] Track ram size, process creation & usage to avoid overflow
- [ ] Add support for Maven
- [ ] Add more color to outputs
- [ ] Prevent file retrieval when there's no `RUNNING` process
