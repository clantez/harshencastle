print("Jars successfully built.\nAttempting to build pom file...")
version = ""; curly_boi = 0
for line in open("build.gradle", "r").read().split("\n"):
    if "version" in line.split("=")[0] and curly_boi == 0: version = line.split("=")[1].split('"')[1]
    if "{" in line: curly_boi += 1
    if "}" in line: curly_boi -= 1
pomfile = open("build/libs/HarshenCastle-" + version + ".pom", "w")
pomfile.write("""<?xml version="1.0" encoding="UTF-8"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId>kenijey.harshencastle</groupId>
  <artifactId>HarshenCastle</artifactId>
  <version>""" + version + """</version>
</project>
""")
pomfile.close()
print("All operations were successfully compleated")