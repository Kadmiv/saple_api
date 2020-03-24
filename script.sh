#!/bin/bash
echo "API Service"
#BASE_PATH="./build/libs/"
BASE_PATH=./app/
APP_NAME=co_share_api
#APP_ARGS="-Xmx1524m -XX:MaxPermSize=256m -Xss2048k --spring.profiles.active=prod"
APP_ARGS=--spring.profiles.active=prod
java -jar  $BASE_PATH$APP_NAME.jar $APP_ARGS
#./gradlew bootRun --args='--server.port=49888'
exit 0