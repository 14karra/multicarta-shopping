Поддерживаемый функционал
====
##### 1) Регистрация пользователя
##### 2) Авторизация пользователя
##### 3) Просмотр всех пользователей
##### 4) Просмотр Продуктов
##### 5) Покупка продукта
##### 6) Получение статистики о покупках
- Вывести список покупок за последнюю неделю
- Вывести самый покупаемый товар за последний месяц
- Вывести имя и фамилию человека, совершившего больше всего покупок за полгода
- Что чаще всего покупают люди в возрасте 18 лет<br>

Способы доступа к приложению
====
Приложение доступно в виде собранного [`Docker` образа](https://hub.docker.com/repository/docker/14karra/shopping),
 и в виде [исходников](https://github.com/14karra/multicarta-shopping).<br> Каждый из которых может быть запушено по желанию. 


Запуск приложения
====
Существует 2 способа запуска приложения:
- Запускать готовый Docker образ
- Собрать собственный образ на основе исходников и запускать его.

Давайте рассмотрим каждый из способов.

#### <b>A) Запуск готового Docker образа</b>

Для запуска готового Docker образа, следует использовать инструкции ниже.<br>
1) запускать Docker Desktop
2) Выполнять следующие комманды последовательно.
- `docker network create -d bridge multicarta_net`
- `docker pull postgres:11`
- `docker run -dit --network=multicarta_net --name PostgreSQL11 -p 5433:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=SHOPPING_DB postgres:11`
- `docker run -dit --network=multicarta_net -p 7000:7000 --name shopping 14karra/shopping:0.0.1`

###### Приложение доступно по адресу http://localhost:7000

#### <b>B) Собрать собственный образ на основе исходников и запускать его.</b>
Для этого варианта, нужно выполнить следующие действия.<br>
1) клонировать исходный код с [репозитория](https://github.com/14karra/multicarta-shopping) Github, в желаемую папку.
3) запускать Docker Desktop.
2) через терминал, переходите в папку, указанную на этапе 1.
3) запускать следующие команды последовательно.
- `docker network create -d bridge multicarta_net`
- `docker pull postgres:11`
- `docker run -dit --network=multicarta_net --name PostgreSQL11 -p 5433:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=SHOPPING_DB postgres:11`  
- `docker build . -t 14karra/shopping:0.0.1`
- `docker run -dit --network=multicarta_net -p 7000:7000 --name shopping 14karra/shopping:0.0.1`

###### Приложение доступно по адресу http://localhost:7000

Спецификация OpenAPI
====
После запуска приложения, можно получить дотуп к OpenAPI спецификации запушенной версии по адресу `http://localhost:7000/swagger-ui.html`.<br>
Скачать YAML спецификацию можно по адресу `http://localhost:7000/shopping-api-spec.yaml`.<br>
Последняя сохраненная YAML спецификация доступна в проекте в файле `./swagger.yaml`.
