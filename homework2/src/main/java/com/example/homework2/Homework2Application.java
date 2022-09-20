package com.example.homework2;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

// url h2 консоли: http://localhost:8080/h2-console

@SpringBootApplication
public class Homework2Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework2Application.class, args);

		try {
			Console.main(args);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
