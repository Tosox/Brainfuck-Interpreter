package de.tosoxdev.brainfuckinterpreter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter();

        if (args.length < 1) {
            interpreter.execute(scanner.nextLine());
        } else {
            interpreter.fromFile(args[0]);
        }
    }
}
