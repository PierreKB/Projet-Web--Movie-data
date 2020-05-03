package com.ms.movie.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.movie.entity.Movie;



public interface MovieRepository extends MongoRepository<Movie, String> {
	
	Optional<Movie> findByTitle(String title);
}
