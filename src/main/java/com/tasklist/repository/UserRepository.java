package com.tasklist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tasklist.models.Task;
import com.tasklist.models.User;

@CrossOrigin(origins = "*", maxAge = 360000)
@RepositoryRestResource

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	List<User> findAll();
	Optional<User>  findById(Long id_user);


	Boolean existsByUsername(String username);
	Boolean existsByPhone(String phone);
	Boolean existsByEmail(String email);
	
}
