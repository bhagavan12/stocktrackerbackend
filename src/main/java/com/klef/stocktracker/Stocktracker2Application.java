package com.klef.stocktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Stocktracker2Application {

	public static void main(String[] args) {
		
		 Dotenv dotenv = Dotenv.load();
		 
		 System.setProperty("DB_URL", dotenv.get("DB_URL"));
	        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
	        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	        System.out.println("DB_URL: " + dotenv.get("DB_URL"));
	        System.out.println("DB_USERNAME: " + dotenv.get("DB_USERNAME")); 
	        System.out.println("DB_PASSWORD: " + dotenv.get("DB_PASSWORD"));
		SpringApplication.run(Stocktracker2Application.class, args);
	}

}
