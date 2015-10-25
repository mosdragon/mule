gameLocation="build/libs/mule-*.jar" # where gameLocation is located

[ -f $gameLocation ] && echo "Game already built" || (echo "Building game:" && gradle build)

echo "Starting game"
java -jar $gameLocation