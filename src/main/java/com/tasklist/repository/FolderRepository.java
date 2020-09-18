package com.tasklist.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tasklist.models.Folder;

import com.tasklist.models.User;


@CrossOrigin("*")
@RepositoryRestResource
@EnableAutoConfiguration
public interface FolderRepository extends JpaRepository<Folder, Long>{

	Optional<Folder> findByUser(User id_user);
	Optional<Folder> findById(Long id_folder);
}
