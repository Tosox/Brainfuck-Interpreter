package de.tosoxdev.brainfuckinterpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BrainfuckInterpreter {
    // Scanner for the ',' operator
    private final Scanner scanner = new Scanner(System.in);
    // Max memory limit
    private static final int MAX_MEMORY_SIZE = 65535;
    // Simulating cell memory
    private final byte[] memory = new byte[MAX_MEMORY_SIZE];

    public String fromFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
            return translate(sb.toString());
        } catch (IOException e) {
            return "Please provide a valid path to the file.";
        }
    }

    public String translate(String code) {
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
                memory[pointer] = (byte)scanner.next().charAt(0);
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

        return output.toString();
    }
}
