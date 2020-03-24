# API

######Если не установлен Git
>sudo apt install git
>
>sudo apt-get update
>
>sudo apt-get upgrade

####Если не установлен Gradle
######Чтобы установить его, нужно много чего скачать )
>apt-get install zip
>
>apt-get install unzip
>
>apt-get install curl
>
>curl -s "https://get.sdkman.io" | bash
>
>source "/root/.sdkman/bin/sdkman-init.sh"
>
>sdk install gradle 5.6.3

####Сборка и запуск
*Заходим в  папку проекта и билдим с помощью команды*
>sudo gradle build

*После этого должны подтянуться все зависимости и собраться проект (могут быть разного рода w - warnings)*

####*В первую очередь нужно запусить базу данных*

*Если есть jar-файл , то переходим к сл. пункту*
>gradle bootJar

*Заходим в папке /build/libs и проверяем наличие файла - app_file_name.jar*

###Cоздание сервиса сервера и запуск 
[Обсуждение тут, если что](http://qaru.site/questions/48061/spring-boot-application-as-a-service)
[или тут](https://fabianlee.org/2018/04/15/java-spring-boot-application-as-a-service-using-systemd-on-ubuntu-16-04/)

*Для запуска сервера в фоновом режиме, нужно создать сервис в папке демонов*

>nano /etc/systemd/system/app_file_name.service

*Закидываем текст ниже, переделываем и сохраняем*

*Пример файла*

[Unit]
Description=API Service

[Service]
##########Type=simple
SuccessExitStatus=143
TimeoutStopSec=10
Restart=always
RestartSec=10
##########Path to project is required
WorkingDirectory=/full_path_to_project
ExecStart=sh /full_path_to_project/script.sh

[Install]
WantedBy=multi-user.target

*Обновим список демонов*
>systemctl daemon-reload

*чтобы сервис работал при загрузке ОС*
>systemctl enable app_file_name.service

*Команды для управления сервисом*
>systemctl start billing
>
>systemctl stop billing
>
>systemctl restart billing
>
>systemctl status billing
>