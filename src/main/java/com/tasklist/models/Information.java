package com.tasklist.models;





import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name="information")
public class Information {

	
	@Id @GeneratedValue
	private Long id_information;
	
	private String title;
	
	private String description;
	
	@DateTimeFormat()
	private Date date;
	
	 @ManyToOne
	 @JoinColumn(name = "id_category")
	 @JsonManagedReference
	 private Category category;
		
	 @ManyToOne
	 @JoinColumn(name = "id_user")
	 @JsonManagedReference
	    private User user;

	public Information() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Information(String title, String description, Date date, Category category, User user) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.category = category;
		this.user = user;
	}


	public Long getId_information() {
		return id_information;
	}

	public void setId_information(Long id_information) {
		this.id_information = id_information;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Information [id_information=" + id_information + ", title=" + title + ", description=" + description
				+ ", date=" + date + ", category=" + category + ", user=" + user + "]";
	}


	public String getTitle() {
		return title;
	}


	public Category getCategory() {
		return category;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	 
	 
}
