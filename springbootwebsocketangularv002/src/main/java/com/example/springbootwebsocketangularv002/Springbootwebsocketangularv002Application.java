package com.example.springbootwebsocketangularv002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Spring Boot WebSocket Angular application.
 * It serves as the entry point for the Spring Boot application.
 * The @SpringBootApplication annotation indicates that this is a Spring Boot application
 * and triggers auto-configuration, component scanning, and other features.
 * Overall, this class bootstraps the application and starts the embedded server.
 * @author Haja Rabemananjara
 */
@SpringBootApplication
public class Springbootwebsocketangularv002Application {

	/**
	 * The main method to run the Spring Boot application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Springbootwebsocketangularv002Application.class, args);
	}

}
