
# Используйте официальный образ OpenJDK для базового слоя
FROM openjdk:17-jdk-slim as build

# Установите рабочий каталог для вашего приложения
WORKDIR /app

# Обновите пакеты и установите необходимые инструменты
RUN apt-get update && apt-get install -y wget unzip

# Скачайте и установите Gradle
RUN wget https://services.gradle.org/distributions/gradle-8.7-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-8.7-bin.zip
ENV GRADLE_HOME=/opt/gradle/gradle-8.7
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# Скопируйте Gradle wrapper и файлы зависимостей в рабочий каталог, если необходимо
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Сделайте скрипт gradlew исполняемым
RUN chmod +x ./gradlew

# Скопируйте исходный код вашего приложения в Docker образ
COPY src src

# Соберите приложение
RUN ./gradlew build -x test


## Устанавливаем зависимости для Chrome
#RUN apt-get install -y fonts-liberation libappindicator3-1 libasound2 libatk-bridge2.0-0 \
#    libatk1.0-0 libcups2 libdbus-1-3 libgdk-pixbuf2.0-0 libnspr4 libnss3 libx11-xcb1 libxcomposite1 \
#    libxdamage1 libxrandr2 xdg-utils libgbm1 libxss1 libcairo2 libpango-1.0-0 libpangocairo-1.0-0 \
#    libx11-6 libxcomposite1 libxcursor1 libxdamage1 libxext6 libxi6 libxrandr2 libxrender1 \
#    libxss1 libxtst6 --no-install-recommends
#
## Загружаем и устанавливаем Google Chrome
#RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
#RUN dpkg -i google-chrome-stable_current_amd64.deb; apt-get -fy install

# Настройте вторую стадию сборки для более легкого образа
FROM openjdk:17-jdk-slim
COPY ./apiNews-0.0.1-SNAPSHOT.jar app.jar
#COPY --from=build /usr/bin/google-chrome /usr/bin/google-chrome
#COPY --from=build /opt/google/chrome /opt/google/chrome

# Обозначьте, что контейнер будет слушать порт 8081
EXPOSE 8081

# Установите точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
