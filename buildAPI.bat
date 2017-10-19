@echo off
cd scripts
call remove_previous.py
cd ..
call gradlew.bat publishToMavenLocal > build.log
cd scripts
call build_pom.py
call temp_batchfile.bat
pause