@echo off
title Brainfuck Interpreter

set arg1=%1
if not defined arg1 set /p arg1="Please provide a path to the Brainfuck file: "
java -jar "./Brainfuck-Interpreter_v1.0.0.jar" %arg1%

pause
