package com.ms.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.movie.dao.MovieRepository;
import com.ms.movie.entity.Movie;
import com.ms.movie.entity.Rating;
import com.ms.movie.exception.RequestException;
import com.ms.movie.messaging.MessageProducer;

@RestController
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	@Autowired
	MessageProducer producer;
	
	
	@RequestMapping(value="/movie/rate/{id}", method = RequestMethod.PUT)
	Movie Rate(@PathVariable("id") String id, @RequestBody Rating rating) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		movie.get().Rate(rating.getValue());
		
		Movie updatedMovie = repository.save(movie.get());
		
		producer.sendUpdate(updatedMovie.getId(), rating);
		
		return updatedMovie;
	}
}