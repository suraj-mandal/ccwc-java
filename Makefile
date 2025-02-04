all: build_target clean

build_target:
	@echo "Generating the jar"
	./gradlew jar
	jar tf build/libs/ccwc.jar
	@echo "Generating the native image of the jar file"
	native-image -jar build/libs/ccwc.jar

clean:
	@echo "Cleaning up the generated jar"
	rm build/libs/*.jar