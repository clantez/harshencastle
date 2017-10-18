print("Attempting to remove Previous versions...")
version = ""; curly_boi = 0
for line in open("build.gradle", "r").read().split("\n"):
    if "version" in line.split("=")[0] and curly_boi == 0: version = line.split("=")[1].split('"')[1]
    if "{" in line: curly_boi += 1
    if "}" in line: curly_boi -= 1
import os
list_of_dirs = [".jar", ".pom", "-api.jar", "-sources.jar"]
for dire in list_of_dirs:
    try: os.remove("build/libs/HarshenCastle-" + version + dire)
    except FileNotFoundError: pass;
print("Successfully removed previous versions.\nAttempting to Building Jars")