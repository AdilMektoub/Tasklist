package com.tasklist.models;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Category {

	
	@Id @GeneratedValue
	private Long id_category;
	
	private String title;
	
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonManagedReference
    private User user;
	
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_category")
    @JsonBackReference
    private List<Information> informations = new ArrayList<>();	

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(String title, User user, List<Information> informations) {
		super();
		this.title = title;
		this.user = user;
		this.informations = informations;
	}


	public Long getId_category() {
		return id_category;
	}

	public void setId_category(Long id_category) {
		this.id_category = id_category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}



	@Override
	public String toString() {
		return "Category [id_category=" + id_category + ", title=" + title + ", user=" + user + ", informations="
				+ informations + "]";
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

}
