# Дипломный проект по профессии "Тестировщик" 

## Документация
- [Текст задания](https://github.com/netology-code/qa-diploma)
- [План автоматизации](https://github.com/SSamsons/Diplomnaya_Rabota/blob/man/Documets/Plan.md)
- [Отчет тестирования](https://github.com/SSamsons/Diplomnaya_Rabota/blob/man/Documets/Report.md)
- [Отчет автоматизации](https://github.com/SSamsons/Diplomnaya_Rabota/blob/man/Documets/Summary.md)

### Требуемое ПО 
1. Яндекс Браузер (23.9.3.931 (64-bit)) / Chrome Версия 119.0.6045.105 (64 бит)
2. IntelliJ IDEA 2022.3.2 (Community Edition)
3. Docker Desktop 4.22.0 (117440)
4. DBeaver 23.1.1.202306251800

## Начало работы

### Клонирование проекта
1. [Перейти](https://github.com/SSamsons/Diplomnaya_Rabota/tree/man) в репозиторий с проектом
2. Нажать на зеленую кнопку `<> Code`
3. Клонировать проект, сохранив его на свое устройство удобным для Вас способом

## Запуск автотестов и получение SUT:
1. Открыть проект в IntelliJ IDEA
2. Запустить Docker Desktop 
3. В терминале IntelliJ IDEA выполнить команду: `docker-compose up --build` 

### Подключение SUT к MySQL:
1. В терминале 2 запустить приложение: ` java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar `
2. Проверить запуск приложения в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"` 
4. Создать отчёт Allure`.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры выполнив команду:`docker-compose down`

### Подключение SUT к PostgreSQL:
1. В терминале 2 запустить приложение: `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
2. Проверить запуск приложения в браузере Chrome:`http://localhost:8080`
3. В терминале 3 запустить тесты: `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
4. Создать отчёт Allure и открыть в браузере `.\gradlew allureServe`
5. Закрыть отчёт в терминале 3: `CTRL + C` -> `y` -> `Enter`
6. Остановить приложение в терминале 2: `CTRL + C`
7. Остановить контейнеры выполнив команду:`docker-compose down`
