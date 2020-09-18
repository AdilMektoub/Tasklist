package com.tasklist.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tasklist.models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="events")
public class Events {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id_events;
	
	String text;
	
	LocalDateTime start;
	
	LocalDateTime end;
	
	@ManyToOne
    @JoinColumn(name = "id_user")
    @JsonManagedReference
	User user;
	
//	@JsonProperty("resource")
//	public Long getUserId() {
//		return user.getId_user();
//	}

	public Long getId() {
		return id_events;
	}

	public void setId(Long id_events) {
		this.id_events = id_events;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
