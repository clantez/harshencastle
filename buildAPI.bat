@echo off
cd scripts
call remove_previous.py
cd ..
call gradlew.bat publishToMavenLocal > build.log
cd scripts
call upload_api.py
call temp_batchfile.bat
cd ..
pause