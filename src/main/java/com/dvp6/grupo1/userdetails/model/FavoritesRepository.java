package com.dvp6.grupo1.userdetails.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
  Classe repositório do Spring Data JPA.
*/
@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {

}