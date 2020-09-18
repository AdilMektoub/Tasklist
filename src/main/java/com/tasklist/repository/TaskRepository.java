package com.tasklist.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tasklist.models.Task;
import com.tasklist.models.User;


@CrossOrigin(origins = "*", maxAge = 360000)
@RepositoryRestResource
//@EnableAutoConfiguration
public interface TaskRepository extends JpaRepository<Task, Long>{
//	List<Task> findByDescriptionContaining(String description);
	List<Task> findAll();
	Optional<Task> findByUser(User user);
	Optional<Task> findByDescription(String description);
//	boolean addTask(Task task);
//	Optional<Task> findByUsername(User user);
	Optional<Task> findById(Long id_task);
//	Optional<Task> addTask(String description, Long id_Folder, Long id_user);
	


	

}
