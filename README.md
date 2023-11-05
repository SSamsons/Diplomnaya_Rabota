https://ci.appveyor.com/api/projects/status/6a0eln3m9yk2t59a?svg=true

# Дипломный проект по профессии "Тестировщик" 

## Документация
- [Текст задания](https://github.com/netology-code/qa-diploma)
- [План автоматизации](https://github.com/SSamsons/Diplomnaya_Rabota/blob/man/Documets/Plan.md)

## Требуемое ПО 
1. Chrome Версия 116.0.5845.111 (64 бит)
2. IntelliJ IDEA 2022.3.2 (Community Edition)
3. Docker Desktop 4.22.0 (117440)
4. DBeaver 23.1.1.202306251800

## Инструкция по запуску автотестов и получения SUT:
1. Клонировать проект: (https://github.com/SSamsons/Diplomnaya_Rabota/tree/man)
2. Открыть проект в IntelliJ IDEA
3. Запустить Docker Desktop 
4. Выполнить в терминале команду: `docker-compose up --build` в терминале IntelliJ IDEA  


### Подключение SUT к MySQL:
1. В терминале 2 запустить приложение: ` java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar `
2. Проверить запуск приложения в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"` 
4. Создать отчёт Allure`.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры в терминале 4:`docker-compose down`

### Подключение SUT к PostgreSQL:
1. В терминале 2 запустить приложение: `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
2. Проверить запуск приложения в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
4. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры в терминале 4:`docker-compose down`
