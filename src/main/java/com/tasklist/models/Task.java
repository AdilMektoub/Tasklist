package com.tasklist.models;






import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_task;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_folder")
//	@JsonManagedReference
	@JsonIgnore
    private Folder folder;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
//    @JsonManagedReference
//    @JsonIgnore
    private User user;

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task( String description, Folder folder, User user) {
		this.description = description;
		this.folder = folder;
		this.user = user;
	}

	public Long getId_task() {
		return id_task;
	}

	public void setId_task(Long id_task) {
		this.id_task = id_task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return "Task [id_task=" + id_task + ", description=" + description + ", folder=" + folder + ", user=" + user
				+ "]";
	}




	


	

	
	
    
    
    
    
}
