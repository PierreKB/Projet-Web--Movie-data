package com.ms.movieloadbalancer.entity;

import java.util.List;

public class Movie {
	
	private String id;
	private String title;
	private String imdbid;
	private List<Rating> rating;
	
	Movie() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdbid() {
		return imdbid;
	}

	public void setImdbId(String imdbId) {
		this.imdbid = imdbId;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> ratings) {
		this.rating = ratings;
	}
}
