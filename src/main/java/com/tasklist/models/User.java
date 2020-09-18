package com.tasklist.models;



import java.util.ArrayList;



import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tasklist.models.Information;




@Entity
@Table(	name = "users", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "username"),
	@UniqueConstraint(columnNames = "email") 
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;
	
	
	@Size(max = 14)
	
	private String phone;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonIgnore
	private Date dateOfBirth;

	@OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user")
	 @JsonIgnore
//	@JsonBackReference
	private List<Events> events = new ArrayList<>();
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user")
//	 @JsonIgnore
//	@JsonBackReference
	private List<Task> tasks = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	 @JsonIgnore
//	@JsonBackReference
	private List<Information> informations = new ArrayList<>();
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	 @JsonIgnore
//	@JsonBackReference
	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	 @JsonIgnore
//	@JsonBackReference
	private List<Folder> folders = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "id_user"), 
				inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Role> roles = new HashSet<>();
	


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User( @NotBlank @Size(min = 3, max = 20) String username,
			@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6) String password,
			 @Size(max = 14) String phone, Date dateOfBirth) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
//		this.tasks=tasks;
		this.dateOfBirth = dateOfBirth;
	}




	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@JsonIgnore
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}





	public List<Events> getEvents() {
		return events;
	}


	public void setEvents(List<Events> events) {
		this.events = events;
	}


	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", dateOfBirth=" + dateOfBirth + ", events=" + events + ", tasks=" + tasks
				+ ", informations=" + informations + ", categories=" + categories + ", folders=" + folders + ", roles="
				+ roles + "]";
	}





	
}
