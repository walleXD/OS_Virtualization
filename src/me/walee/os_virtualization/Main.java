package me.walee.os_virtualization;

public class Main {
    private  static void bootstrap() {
        InputHandler inputHandler = new InputHandler();
        inputHandler.start();
    }

    public static void main(String[] args) {
        bootstrap();
    }
}
