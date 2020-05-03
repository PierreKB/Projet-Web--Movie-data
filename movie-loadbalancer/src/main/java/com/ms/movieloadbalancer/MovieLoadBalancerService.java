package com.ms.movieloadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.movieloadbalancer.entity.Rating;
import com.ms.movieloadbalancer.entity.Movie;

@RestController
public class MovieLoadBalancerService {
	
	@Autowired
	private RestTemplate template;
	
	@RequestMapping("/request/{id}")
	Movie getMovie(@PathVariable("id") String id) {
		
		Movie movie = template.getForObject(
				"http://MOVIE-DATA/movie/" + id, Movie.class);
		
		return movie;
	}
	
	@RequestMapping("/request/rating/avg/{id}")
	double getAvgRating(@PathVariable("id") String id) {
		
		double avg = template.getForObject(
				"http://MOVIE-DATA/movie/rating/avg/" + id, double.class);
		
		return avg;
	}
	
	@RequestMapping("/request/rating/avgByYear/{id}")
	String getAvgRatingByYear(@PathVariable("id") String id) {
		
		String avgByYear = template.getForObject(
				"http://MOVIE-DATA/movie/rating/avgByYear/" + id, String.class);
		
		return avgByYear;
	}
	
	@RequestMapping("/request/rating/statistics/{id}")
	Map<Double, Integer> getNumberOfRatingByRatingIncrements(@PathVariable("id") String id) {
		
		Map<Double, Integer> avgByInc = new HashMap<Double, Integer>();
		
		avgByInc = template.getForObject(
				"http://MOVIE-DATA/movie/rating/statistics/" + id, avgByInc.getClass());
		
		return avgByInc;
	}
	
	@RequestMapping("/request/rating/total/{id}")
	int getNumberOfRating(@PathVariable("id") String id) {
		
		int total = template.getForObject(
				"http://MOVIE-DATA/movie/rating/total/" + id, int.class);
		
		return total;
	}

	
	@RequestMapping("/request/writers/{id}")
	List<String> getWriters(@PathVariable("id") String id) {
		
		List<String> writers = new ArrayList<String>();
		
		writers = template.getForObject(
				"http://MOVIE-DATA/movie/writers/" + id, writers.getClass());
		
		return writers;
	}
	
	@RequestMapping("/request/directors/{id}")
	List<String> getDirectors(@PathVariable("id") String id) {
		
		List<String> directors = new ArrayList<String>();
		
		directors = template.getForObject(
				"http://MOVIE-DATA/movie/directors/" + id, directors.getClass());
		
		return directors;
	}
	
	@RequestMapping("/request/actors/{id}")
	List<String> getActors(@PathVariable("id") String id) {
		
		List<String> actors = new ArrayList<String>();
		
		actors = template.getForObject(
				"http://MOVIE-DATA/movie/actors/" + id, actors.getClass());
		
		return actors;
	}
	
	@RequestMapping("/request/shortplot/{id}")
	String getShortPlot(@PathVariable("id") String id) {
		
		String plot = template.getForObject(
				"http://MOVIE-DATA/movie/shortplot/" + id, String.class);
		
		return plot;
	}
	
	@RequestMapping("/request/genre/{id}")
	String getGenre(@PathVariable("id") String id) {
		
		String genre = template.getForObject(
				"http://MOVIE-GENRE/movie/genre/" + id, String.class);
		
		return genre;
	}
	
	@RequestMapping(value="/request/rate/{id}", method = RequestMethod.PUT)
	Movie Rate(@PathVariable("id") String id, @RequestBody Rating rating) {
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON); 
	    
	    HttpEntity<Rating> entity = new HttpEntity<Rating>(rating, headers); 
	    ResponseEntity<Movie> response = template.exchange("http://MOVIE/movie/rate/" + id, HttpMethod.PUT, entity, Movie.class, id);
		
		return response.getBody();
	}
}
