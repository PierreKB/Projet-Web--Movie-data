package com.ms.moviegenre.dao;

import org.springframework.data.repository.CrudRepository;

import com.ms.moviegenre.entity.MovieGenre;

public interface MovieGenreRepository extends CrudRepository<MovieGenre, String> {

}
