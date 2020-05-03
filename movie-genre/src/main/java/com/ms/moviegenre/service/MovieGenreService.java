package com.ms.moviegenre.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ms.moviegenre.exception.RequestException;
import com.ms.moviegenre.dao.MovieGenreRepository;
import com.ms.moviegenre.entity.MovieGenre;

@RestController
public class MovieGenreService {
	
	@Autowired
	MovieGenreRepository repository;
	
	@RequestMapping("/movie/genre/{id}")
	MovieGenre getGenre(@PathVariable("id") String id) {
		
		Optional<MovieGenre> genre = repository.findById(id);
		
		if(genre.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return genre.get();
	}
}
