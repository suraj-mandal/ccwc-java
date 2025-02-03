all: build_target clean

build_target:
	@echo "Compiling the java main file"
	javac -d build src/main/java/org/example/Main.java
	@echo "Doing the build"
	jar --create --file ccwc.jar --main-class org.example.Main -C build .
	@echo "Generating the executable jar"
	jar tf ccwc.jar
	@echo "Generating the native image of the jar file"
	native-image -jar ccwc.jar

clean:
	@echo "Cleaning up the generated jar"
	rm *.jar