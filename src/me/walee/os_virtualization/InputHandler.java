package me.walee.os_virtualization;

import java.util.Scanner;

public class InputHandler {
    private Boolean initiated;
    private OS virtualOS;
    private Scanner reader = new Scanner(System.in);

    public InputHandler() {
        initiated = true;

        // get ram size
        System.out.print("RAM Size (in bytes): ");
        Integer ramSize = Integer.parseInt(reader.nextLine());

        // get disk count
        System.out.print("Number of disks: ");
        Integer diskCount = Integer.parseInt(reader.nextLine());

        virtualOS = new OS(ramSize, diskCount);
    }

    public void start() {
        while(initiated) getInput();
    }

    private void getInput() {
        String input;
        System.out.print("> ");

        input = reader.nextLine();

        handleCommand(input.split(" "));

    }

    private void handleCommand(String[] input) {
        switch (input[0]) {
            case "help": helpCommand(); break;
            case "exit": exitCommand(); break;
            default: unknownCommand(); break;
        }
    }

    private void helpCommand() {
        System.out.println("help: Lists all available commands");
        System.out.println("exit: Exits Virtualization");
    }

    private void exitCommand() {
        reader.close();
        initiated = false;
    }

    private void unknownCommand() {
        System.out.println("Unknown Command");
        System.out.println("Please run help to see all available commands");
    }
}
