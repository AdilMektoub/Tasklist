package com.tasklist.ship.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tasklist.models.Task;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String phone;
	
	private List<String> roles;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_task")
	private Collection<?> list;
//	private List<Task> tasks;
	
	
	private Collection<?> infTitle;
	private Collection<?> infDescription;
	
	@ManyToOne
    @JoinColumn(name = "id_folder")
	private Collection<?> foldTitle;
	
	private Collection<?> cat;
	
	
	
//	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
//		this.token = accessToken;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		
//		this.roles = roles;
//	}

	public JwtResponse(String accessToken, Long id, String username, String email, String phone,
			Collection<?> list ,Collection<?> infTitle, Collection<?> infDescription,Collection<?> foldTitle,Collection<?> cat,List<String> roles) {
		super();
		this.token = accessToken;
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
//		this.tasks=tasks;
		this.list= list;
		this.infTitle= infTitle;
		this.infDescription=infDescription;
		this.cat=cat;
		this.foldTitle=foldTitle;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getRoles() {
		return roles;
	}



//	public List<Task> getTasks() {
//		return tasks;
//	}
//
//	public void setTasks(List<Task> tasks) {
//		this.tasks = tasks;
//	}





	public Collection<?> getInfTitle() {
		return infTitle;
	}

	public Collection<?> getList() {
		return list;
	}

	public void setList(Collection<?> list) {
		this.list = list;
	}

	public void setInfTitle(Collection<?> infTitle) {
		this.infTitle = infTitle;
	}
	
	

	public Collection<?> getInfDescription() {
		return infDescription;
	}


	public Collection<?> getFoldTitle() {
		return foldTitle;
	}

	public void setFoldTitle(Collection<?> foldTitle) {
		this.foldTitle = foldTitle;
	}

	public Collection<?> getCat() {
		return cat;
	}

	public void setCat(Collection<?> cat) {
		this.cat = cat;
	}

	
	
	
	
}