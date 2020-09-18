package com.tasklist.security.services;

import java.util.ArrayList;




import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tasklist.models.Category;
import com.tasklist.models.Folder;
import com.tasklist.models.Information;
import com.tasklist.models.Task;
import com.tasklist.models.User;


public class UserDetailsImplement implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id_user;

	private String username;

	private String email;
	private String phone;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	private  List<Task> tasks = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	private List<Information> informations = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_user") 
	private List<Folder> folders = new ArrayList<>();
	
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
//	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "id_user")
	private Collection<?> list;
	
	private Collection<?> infTitle;
	private Collection<?> infDescription;
	private Collection<?> cat;
	
	@ManyToOne
    @JoinColumn(name = "id_folder")
	private Collection<?> foldTitle;


	public UserDetailsImplement(Long id_user, String username, String email,String password,
			String phone,Collection<?> list, Collection<?> infTitle,Collection<?> infDescription,Collection<?> foldTitle,Collection<?> cat, 
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.list=list;
		this.infTitle = infTitle;
		this.infDescription=infDescription;
		this.cat = cat;
		this.foldTitle= foldTitle;
		this.password = password;
		this.authorities = authorities;
		
	}
	
	public static UserDetailsImplement build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
		
	
		List<String> foldTitle = user.getFolders().stream()
				.map(folders->folders.getTitle())
				.collect(Collectors.toList());
		
	
		List<String> list = user.getTasks().stream()
				.map(tasks->tasks.getDescription())
				.collect(Collectors.toList());
		
		
		List<String> infTitle = user.getInformations().stream()
				.map(informations->informations.getTitle())
				.collect(Collectors.toList());
		List<String> infDescription= user.getInformations().stream()
				.map(informations->informations.getDescription())
				.collect(Collectors.toList());
		
		List<String> cat = user.getCategories().stream()
				.map(categories->categories.getTitle())
				.collect(Collectors.toList());
		
		

		return new UserDetailsImplement(
				user.getId_user(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
			user.getPhone(),
			list,
			infTitle,
			infDescription,
			foldTitle,
			cat,
				authorities
				);
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public Collection<?> getList() {
		return list;
	}

	public void setList(Collection<?> list) {
		this.list = list;
	}

	public Collection<?> getInfTitle() {
		return infTitle;
	}


	public Collection<?> getCat() {
		return cat;
	}

	
	public Collection<?> getFoldTitle() {
		return foldTitle;
	}

	

	public Collection<?> getInfDescription() {
		return infDescription;
	}



	public Long getId_user() {
		return id_user;
	}

//	public List<Task> getTasks() {
//		return tasks;
//	}
//
//	public void setTasks(List<Task> tasks) {
//		this.tasks = tasks;
//	}

	public String getPhone() {
		return phone;
	}




	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImplement user = (UserDetailsImplement) o;
		return Objects.equals(id_user, user.id_user);
	}


}
