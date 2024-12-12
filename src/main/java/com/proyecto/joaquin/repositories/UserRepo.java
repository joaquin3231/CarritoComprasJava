package com.proyecto.joaquin.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.joaquin.models.User;


@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
