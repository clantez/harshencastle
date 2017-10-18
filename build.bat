@echo off
call remove_previous.py
call gradlew.bat publishToMavenLocal > nul
call build_pom.py
pause