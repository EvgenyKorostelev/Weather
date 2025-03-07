Weather — это учебный проект Rest Api приложения, для получения информации о погоде,
реализованный на Java с использованием фреймворка Spring Boot. Приложение включает функционал регистрации
пользователя в системе и получением сведений о погоде в выбранном городе.

## Используемые технологии
* Java 21 — основной язык программирования.
* Spring Boot — реализации REST API.
* Project Lombok - для сокращения кода.
* Docker — для контейнеризации и упрощения развертывания.
* JUnit и Mockito — для модульного тестирования бизнес-логики.

## Расположение
* Weather - приложение занимает порт 8088 (http://localhost:8088).

### UserController
* Post запросы на адрес http://localhost:8088/api/user/registration для регистрации пользователя.
* Get запросы на адрес http://localhost:8088/api/user/{userName:\S+} для получения сведений о зарегистрированном
пользователе.
* Get запросы на адрес http://localhost:8088/api/user/users для получения списка всех зарегистрированных пользователей.
* Delete запросы на адрес http://localhost:8088/api/user/{userName:\S+} удаление пользователя.

### WeatherRestController
* Get запросы на адрес http://localhost:8088/api/weather/{cityName:\S+} для получения сведений о погоде
в указанном городе.
* Get запросы на адрес http://localhost:8088/api/weather/update для обновления погоды в сохраненных городах
и получение актуализированного списка.

===========================================================================

## Установка и запуск
1. ### Скачать данный репозиторий на машину, для запуска.
2. ### Создать образ Docker контейнера.
    ```
    docker build -t weather_api .

    ```
3. ### Запустить контейнер с программой.
    ```
    docker run --name Weather_API -d -p 8088:8088 weather_api

    ```
    ### Приложение готово к работе.  
4. ### Регистрация пользователей
   * Отправить на адрес http://localhost:8088/api/user/registration данные пользователя в теле POST запроса
    ```
    {
    "userName": "Имя_пользователя",
    "apiKey": "API_ключ"
    }
    ```
   * Поля не должны быть пустыми
   * Ограничение имени пользователя от 3 до 10 символов
   * Апи ключ не больше 500 символов
5. ### Получение сведений о погоде
   * Отправить на адрес http://localhost:8088/api/weather/Город имя зарегистрированного в программе пользователя
   в теле GET запроса
    ```
    {
    "userName": "Имя_пользователя"
    }
    ```
