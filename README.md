# Kafka-Angular

This is sample implementation of a SpringBoot backend running with Apache Kafka and WebSocket, producing and consuming a message stream. And a frontend implemented in Angular to call an API and consume websocket messages.

## Stack

- SpringBoot 3.1.4
- Docker Compose
- Apache Kafka
- Zookeper
- Angular 15

## Startup environment

Execute the command below, to start the docker's containers, described on `docker-compose.yml`:

> docker-compose up -d

## Start the backend

Execute the command:

> ./mvnw spring-boot:run


## Start the frontend

Inside of `frontend` folder, execute the command below:

> ng serve

Open the URL: [http://localhost:4200](http://localhost:4200)