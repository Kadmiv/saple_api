echo ON
echo "API Service"
set message=Hello World
:: set BASE_PATH="./build/libs/"
set BASE_PATH=./app/
set APP_NAME=co_share_api
:: set APP_ARGS="-Xmx1524m -XX:MaxPermSize=256m -Xss2048k --spring.profiles.active=prod"
set APP_ARGS=--spring.profiles.active=prod
start java -jar %BASE_PATH%%APP_NAME%.jar %APP_ARGS%