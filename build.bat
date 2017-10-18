@echo off
call remove_previous.py
call gradlew.bat publishToMavenLocal
call build_pom.py
pause