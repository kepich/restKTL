Система запускается с помощью Docker.
Перед сборкой образа необходимо скорректировать адрес и назавние базы данных в файле
"/src/main/resources/application.properties" в строке №1

Сборка образа производится с помощью плагина Spring: 
UNIX:       ./gradlew jibDockerBuild --image=<Имя образа>
WINDOWS:    gradlew jibDockerBuild --image=<Имя образа>

После сборки образа необходимо создать базу данных PostgreSQL с заданным названием.
Запуск приложения производится командой: docker run -p <Порт>:<Порт> -t <Назавние контейнера>
Приложение запущено!

Приложение может обрабатывать GET запросы в браузере по адресам: 

Не требующие авторизации:
"/tasks"    - Список задач
"/tag/all"  - Список тегов

Требующие авторизации:
"/greeting" - Заглушка
"/get/{id}" - Информация о теге по id


POST запросы (данные передаются в теле запроса в формате JSON, все требуют авторизацию):
"/tag"       - Создание нового тега. 
    Пример: POST http://localhost:8080/tag/  {"title": "testrest_15"}
"/tag{id}"   - Изменение тега.
    Пример: POST http://localhost:8080/tag/20  {"title": "testrest_15"}
"/task"      - Создание новой задачи. 
    Пример: POST http://localhost:8080/task/  {"name": "admin_3", "description": "test_task", "task_date": "2020-02-01", "taguid": 0}
"/task/{id}" - Изменение задачи. 
    Пример: POST http://localhost:8080/tag/  {"name": "edited_1", "description": "test_task_trird", "task_date": "2020-02-02", "taguid": 1}


DELETE запросы (Все требуют авторизацию):
"/tag/20"   - Каскадное даление тега со всеми прикрепленными к нему задачами.
    Пример: POST http://localhost:8080/tag/20
"/task/20"  - Удаление задачи. 
    Пример: POST http://localhost:8080/task/20

Авторизация осуществляется с помощью стандартной учетной записи:
login: admin
password: admin

Сервис реализует 