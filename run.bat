@echo off
echo Compiling...
javac -cp ".;sqlite.jar;flatlaf.jar" *.java
if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b
)
echo Running application...
java -cp ".;sqlite.jar;flatlaf.jar" Launcher
pause