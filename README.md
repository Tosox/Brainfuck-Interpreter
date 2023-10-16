# Brainfuck Interpreter

A simple Brainfuck interpreter written in Java. Brainfuck is an esoteric programming language known for its minimalistic design, consisting of only eight commands.

## Features

* Interprets Brainfuck code from the command line or a file
* Supports the standard Brainfuck commands: `>`, `<`, `+`, `-`, `.`, `,`, `[`, and `]`
* Simulates cell memory with a maximum size of 65,535 cells
* Interactive mode if no file is provided
* Outputs the interpreted results as text in the command line

## Usage

You can use this Brainfuck interpreter in two ways: interactive mode and file mode
* Interactive mode: enter your Brainfuck code when prompted
* File mode: Pass the path to the Brainfuck file as the first argument

## Brainfuck Commands

* `>`: Increment the data pointer.
* `<`: Decrement the data pointer.
* `+`: Increment the byte at the data pointer.
* `-`: Decrement the byte at the data pointer.
* `.`: Output the byte at the data pointer as a character.
* `,`: Read a character and store it in the byte at the data pointer.
* `[`: Jump forward to the command after the matching `]` if the byte at the data pointer is zero.
* `]`: Jump back to the command after the matching `[` if the byte at the data pointer is nonzero.

## Example

An example Brainfuck code to print "Hello, World!":

```brainfuck
++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++.>++++.------.--------.>+.>.
```

Run the interpreter as shown in the usage instructions to see the output.
