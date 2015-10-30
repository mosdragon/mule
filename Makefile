gameFile=build/libs/mule-*.jar # where gameFile is located

all:
	@gradle build
	@echo "Build complete"

play:
	@java -jar $(gameFile)

clean:
	@rm -rf build
	@echo "Build files cleaned up"


