package me.walee.os_virtualization;

import java.util.Scanner;

public class InputHandler {
    private Boolean initiated = false;
    private Scanner reader = new Scanner(System.in);

    public void start() {
        initiated = true;

        while(initiated) getInput();
    }

    private void getInput() {
        System.out.print("> ");
        handleCommand(reader.nextLine().split(" "));
    }

    private void handleCommand(String[] input) {
        switch (input[0]) {
            case "help": helpCommand(); break;
            case "exit": exitCommand(); break;
            default: unknownCommand(); break;
        }
    }

    private void helpCommand() {}

    private void exitCommand() {
        reader.close();
        initiated = false;
    }

    private void unknownCommand() {
        System.out.println("Unknown Command");
        System.out.println("Please run help to see all available commands");
    }
}
