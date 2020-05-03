package com.ms.movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.movie.dao.MovieRepository;
import com.ms.movie.entity.Movie;
import com.ms.movie.entity.Rating;
import com.ms.movie.entity.cast.ActorShort;
import com.ms.movie.entity.cast.CastShortItem;
import com.ms.movie.entity.cast.FullCastData;
import com.ms.movie.entity.wikidata.WikipediaData;
import com.ms.movie.exception.RequestException;

@RestController
public class MovieService {
	
	@Autowired
	MovieRepository repository;
	
	@RequestMapping("/movie/{id}")
	Movie getMovie(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get();
	}
	
	@RequestMapping("/movie/getByTitle/{title}")
	Movie getMovieByTitle(@PathVariable("title") String title) {
		
		Optional<Movie> movie = repository.findByTitle(title);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with title " + title;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get();
	}
	
	@RequestMapping("/movie/titles")
	List<String> getMoviesTitles() {
		
		List<Movie> movies = repository.findAll();
		List<String> titles = new ArrayList<String>();
		
		for(Movie movie : movies) {
			titles.add(movie.getTitle());
		}
		
		return titles;
	}
	
	
	@RequestMapping("/movie/rating/avg/{id}")
	double getAvgRating(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get().AvgRating();
	}
	
	@RequestMapping("/movie/rating/avgByYear/{id}")
	Map<Integer, Double> getAvgRatingByYear(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get().AvgRatingByYear();
	}
	
	@RequestMapping("/movie/rating/statistics/{id}")
	Map<Double, Integer> getNumberOfRatingByRatingIncrements(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get().NumberOfRatingByRatingIncrements();
	}
	
	@RequestMapping("/movie/rating/total/{id}")
	int getNumberOfRating(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		return movie.get().getRating().size();
	}

	
	@RequestMapping("/movie/writers/{id}")
	List<String> getWriters(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		String url = "https://imdb-api.com/en/API/FullCast/" + System.getenv("IMDBAPI_KEY") + "/tt" + movie.get().getImdbid();
		FullCastData cast =  new RestTemplate().getForObject(url, FullCastData.class);
		
		List<String> names = new ArrayList<String>();
		
		for(CastShortItem item : cast.getWriters().getItems())
		{
			names.add(item.getName());
		}
		
		return names;
	}
	
	@RequestMapping("/movie/directors/{id}")
	List<String> getDirectors(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		String url = "https://imdb-api.com/en/API/FullCast/" + System.getenv("IMDBAPI_KEY") + "/tt" + movie.get().getImdbid();
		FullCastData cast =  new RestTemplate().getForObject(url, FullCastData.class);
		
		List<String> names = new ArrayList<String>();
		
		for(CastShortItem item : cast.getDirectors().getItems())
		{
			names.add(item.getName());
		}
		
		return names;
	}
	
	@RequestMapping("/movie/actors/{id}")
	List<String> getActors(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		String url = "https://imdb-api.com/en/API/FullCast/" + System.getenv("IMDBAPI_KEY") + "/tt" + movie.get().getImdbid();
		FullCastData cast =  new RestTemplate().getForObject(url, FullCastData.class);
		
		List<String> names = new ArrayList<String>();
		
		for(ActorShort item : cast.getActors())
		{
			names.add(item.getName());
		}
		
		return names;
	}
	
	@RequestMapping("/movie/shortplot/{id}")
	String getShortPlot(@PathVariable("id") String id) {
		
		Optional<Movie> movie = repository.findById(id);
		
		if(movie.isEmpty()) {
			String errorMsg = "No movie found with id " + id;
			
			throw new RequestException(RequestException.ID_NOT_FOUND, errorMsg);
		}
		
		String url = "https://imdb-api.com/en/API/Wikipedia/" + System.getenv("IMDBAPI_KEY") + "/tt" + movie.get().getImdbid();
		WikipediaData data = new RestTemplate().getForObject(url, WikipediaData.class);
		
		return data.getPlotShort().getPlainText();
	}
	
	
	public void updateRating(String movieId, Rating rating) {
		
		Movie movie = repository.findById(movieId).get();
		
		movie.Rate(rating.getValue());
		repository.save(movie);
		
	}
}