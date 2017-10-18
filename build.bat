@echo off
call remove_previous.py
call gradlew.bat publishToMavenLocal > build.log
call build_pom.py
pause