@echo off
REM Delete all .class files in the current directory and subdirectories
echo Deleting all .class files...
del /s /q *.class

REM Delete all .db files in the current directory and subdirectories
echo Deleting all .db files...
del /s /q *.db

echo All .class and .db files deleted.
pause
