package de.tosoxdev.brainfuckinterpreter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrainfuckInterpreter interpreter = new BrainfuckInterpreter();

        String translated;
        if (args.length < 1) {
            System.out.println("Enter the code to interpret:");
            translated = interpreter.translate(scanner.nextLine());
        } else {
            translated = interpreter.fromFile(args[0]);
        }

        System.out.println("Output:");
        System.out.println(translated);
    }
}