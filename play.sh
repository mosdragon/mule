
gamejar="build/libs/mule-*.jar" # where gamejar is located

if [ ! -f  "$gamejar" ]
then
  echo "Building the game"
  gradle build
fi

echo "Starting your game"
java -jar $gamejar