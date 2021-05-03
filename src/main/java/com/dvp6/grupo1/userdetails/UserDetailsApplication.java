package com.dvp6.grupo1.userdetails;

import com.dvp6.grupo1.userdetails.model.FavoritesEntity;
import com.dvp6.grupo1.userdetails.model.UserEntity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/*
  Classe Main do projeto.
*/
@SpringBootApplication()
@ComponentScan(basePackages = "com.dvp6.grupo1.userdetails.*")
public class UserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailsApplication.class, args);
	}

	@Bean
	public FavoritesEntity favoritesEntity() {
		return new FavoritesEntity();
	}

	@Bean
	public UserEntity userEntity() {
		return new UserEntity();
	}

}
