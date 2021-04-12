package com.dvp6.grupo1.userdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class UserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserDetailsApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8084"));
        app.run(args);
	}

}
