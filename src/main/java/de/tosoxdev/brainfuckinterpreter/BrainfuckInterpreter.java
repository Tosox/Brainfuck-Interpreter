package de.tosoxdev.brainfuckinterpreter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class BrainfuckInterpreter {
    // Scanner for the ',' operator
    private final Scanner scanner = new Scanner(System.in);
    // Max memory limit
    private static final int MAX_MEMORY_SIZE = 65535;
    // Simulating cell memory
    private final byte[] memory = new byte[MAX_MEMORY_SIZE];

    public void fromFile(String filepath) {
        try {
            String contents = Files.readString(Paths.get(filepath));
            execute(contents);
        } catch (Exception e) {
            System.out.println("Please provide a valid path to the file.");
        }
    }

    public void execute(String code) {
        StringBuilder output = new StringBuilder();

        int pointer = 0;
        Arrays.fill(memory, (byte) 0);

        int conditionValue = 0;

        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '>') {
                // '>' increments the data pointer
                // If the pointer is at the rightmost memory, it becomes zero
                pointer = (pointer == MAX_MEMORY_SIZE - 1) ? 0 : pointer + 1;
            } else if (code.charAt(i) == '<') {
                // '<' decrements the data pointer
                // If the pointer is at the leftmost memory, it is returned to the rightmost memory
                pointer = (pointer == 0) ? MAX_MEMORY_SIZE - 1 : pointer - 1;
            } else if (code.charAt(i) == '+') {
                // '+' increments the byte at the data pointer
                memory[pointer]++;
            } else if (code.charAt(i) == '-') {
                // '-' decrements the byte at the data pointer
                memory[pointer]--;
            } else if (code.charAt(i) == '.') {
                // '.' outputs the byte at the data pointer
                output.append((char)memory[pointer]);
            } else if (code.charAt(i) == ',') {
                // ',' stores a byte of input in the byte at the data pointer
                System.out.print(output);
                output.delete(0, output.length());
                memory[pointer] = (byte) scanner.next().charAt(0);
            } else if (code.charAt(i) == '[') {
                // '[' jumps forward to the command after the matching ']' command
                // if the byte at the data pointer is zero
                if (memory[pointer] == 0) {
                    i++;
                    while ((conditionValue > 0) || (code.charAt(i) != ']')) {
                        if (code.charAt(i) == '[') {
                            conditionValue++;
                        } else if (code.charAt(i) == ']') {
                            conditionValue--;
                        }
                        i++;
                    }
                }
            } else if (code.charAt(i) == ']') {
                // ']' jumps back to the command after the matching '[' command
                // if the byte at the data pointer is nonzero
                if (memory[pointer] != 0) {
                    i--;
                    while ((conditionValue > 0) || (code.charAt(i) != '[')) {
                        if (code.charAt(i) == ']') {
                            conditionValue++;
                        } else if (code.charAt(i) == '[') {
                            conditionValue--;
                        }
                        i--;
                    }
                    i--;
                }
            }
        }

        System.out.println(output);
    }
}
