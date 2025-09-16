package edu.ccrm.cli;
import java.nio.file.*;
import java.util.Scanner;
import java.io.IOException;
public class DemoRunner {
    private final CliMenu menu = new CliMenu();
    public void runDemo() {
        System.out.println("[DEMO MODE] Running scripted inputs from data/demo-input.txt...");
        try(Scanner demoScanner = new Scanner(Paths.get("data/demo-input.txt"))) {
            menu.start(demoScanner, true);
        } catch(IOException e) {
            System.out.println("Demo file not found: " + e.getMessage());
        }
    }
}
