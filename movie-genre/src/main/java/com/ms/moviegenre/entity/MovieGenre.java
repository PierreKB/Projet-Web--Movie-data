package com.ms.moviegenre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieGenre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	@Column(nullable = false)
	private String genre;
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
		
}
