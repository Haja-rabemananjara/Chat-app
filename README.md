# Chat-app
Chat-app To developp this chat-app, we will use Springboot + Websocket + Angular

## Specs
### Backend
Java Springboot.
We will use maven. It is a management Java tool (build and automation tool).

#### Initialization

Be sure that maven is installed :
mvn --version or sudo apt install maven

Link: https://start.spring.io/

Setup to generate Project : Maven Langage : Java Spring Boot: 4.0.1

Project Metadata:
- Group : com.example
- Artifact : springbootwebsocketangularv002
- Name: springbootwebsocketangularv002
- Description : Demo project for Spring Boot
- Package name : com.example.springbootwebsocketangularv002

Packaging : Jar Java: 17

Dependencies :
- Spring Web
- Lombok
- WebSocket

#### Spring Boot
Java Spring Boot is a open source tool which help the using of Java to create microservices and web application.

#### Websocket
The API WebSocket is an advanced technology that allows opening a bidirectional communication channel between a browser (client side) and a server.

In other words, It is a communication protocol that provides full-duplex, bi-directional communication channels over a single TCP connection, allowing real-time data transfer between client and server.

#### Launch 
When backend is ready launch commands:
- Rebuild the project from zero, and launch the tests and product a .jar
mvn clean install

- Start the server Spring Boot on .spring dev mode.
mvn spring-boot:run

### Frontend
Angular

### Others

#### STOMP

#### SockJS