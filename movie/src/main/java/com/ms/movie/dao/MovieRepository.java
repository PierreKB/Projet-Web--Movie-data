package com.ms.movie.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.movie.entity.Movie;


public interface MovieRepository extends MongoRepository<Movie, String> {
	
}
