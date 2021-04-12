package com.dvp6.grupo1.userdetails.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String>{
    //List<User> findTop(String userId);
    Optional<User> findById(long userId);
}
