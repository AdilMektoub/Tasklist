package com.tasklist.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tasklist.models.Information;
import com.tasklist.models.Task;


@CrossOrigin("*")
@RepositoryRestResource
public interface InformationRepository extends JpaRepository<Information, Long>{
	Optional<Information> findById(Long id_information);

}
