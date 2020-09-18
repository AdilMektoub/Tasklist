package com.tasklist.models;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tasklist.models.Task;



@Entity
@Table(name="folder")
public class Folder {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_folder;
	
	private String title;
	
	private boolean isProject;
	
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonManagedReference
    private User user;
	
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_folder")
//    @JsonBackReference
    private List<Task> tasks = new ArrayList<>();	

	public Folder() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public Folder(String title,  User user, List<Task> tasks) {
	
		super();
		this.title = title;
		
		this.user = user;
		this.tasks = tasks;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Long getId_folder() {
		return id_folder;
	}

	public User getUser() {
		return user;
	}

	public void setId_folder(Long id_folder) {
		this.id_folder = id_folder;
	}

	public void setUser(User user) {
		this.user = user;
	}




	@Override
	public String toString() {
		return "Folder [id_folder=" + id_folder + ", titleFolder=" + title + ", user=" + user + ", task=" + tasks
				+ "]";
	}

	

	

	
}
