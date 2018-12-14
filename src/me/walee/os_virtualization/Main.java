package me.walee.os_virtualization;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        inputHandler.start();
    }
}

// TODO: Catch running out of threads when trying to retrieve files.