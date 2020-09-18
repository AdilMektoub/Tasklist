package com.tasklist.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.tasklist.models.Events;
import com.tasklist.models.User;

public interface EventsRepository extends JpaRepository<Events, Long>{

	@Query("from Events e where not(e.end < :from and e.start > :to)")
	public List<Events> findBetween(@Param("from") LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);

	List<Events> findAll();
	
//	public void save(Events e);
}
