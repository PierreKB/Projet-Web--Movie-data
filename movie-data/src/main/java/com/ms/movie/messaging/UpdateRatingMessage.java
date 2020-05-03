package com.ms.movie.messaging;

import com.ms.movie.entity.Rating;

public class UpdateRatingMessage {
	
	private String movieId;
	private Rating rating;
	
	public UpdateRatingMessage(String movieId, Rating rating) {
		this.movieId = movieId;
		this.rating = rating;
	}
	
	public UpdateRatingMessage() {}
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}

}
