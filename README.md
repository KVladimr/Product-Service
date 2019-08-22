# Product-Service
Реализация тестового задания на Springboot и Spring Data Mongodb

# How to Run Service
Product-service выложен на DockerHub. Запустить можно двумя способами:

  - Напрямую:
    Запустить Mongo, если уже не запущен
    ```
    $ docker run --name some-mongo -d mongo
    ```
    Затем запустить product-service с помощью следующей комманды:
    ```sh
    $ docker run --name any-name --link some-mongo:mongodb -d -p 8080:8080 enekst/product-service
    ```
  - С помощью docker-compose.yml:
    Сохранить docker-compose.yml и в директории с сохраненным файлом вызвать команду
    ```sh
    $ docker-compose up -d
    ```

# curl 
  - Создание нового продукта:
    ```
    $ curl -H "Content-Type: application/json" -X POST -d '{"name": "some-name", "description": "some-description", "parameters": {"par1": "par1-value", "par2": "par2-value"}}' http://localhost:8080/product/
    ```
  - Поиск товаров по значению параметра:
    ```
    $ curl "http://localhost:8080/product/search?parameter=par1&value=par1-value"
    ```
  - Получение деталей продукта (по id):
    ```
    $ curl http://localhost:8080/product/some-id
    ```
