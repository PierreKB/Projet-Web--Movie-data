package com.ms.movie.entity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {
	
	@Id
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
	
	public double AvgRating() {
		double avg = 0.0;
		
		for(Rating r : rating) {
			avg += r.getValue();
		}
		
		return (avg / rating.size());
	}
	
	public Map<Integer, Double> AvgRatingByYear() {
		
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		Map<Integer, Integer> entrySize = new HashMap<Integer, Integer>();
		
		for(Rating r : rating) {
			
			if(map.get(r.getYear()) == null) {
				map.put(r.getYear(), 0.0);
				entrySize.put(r.getYear(), 0);
			}
			
			map.replace(r.getYear(), map.get(r.getYear()).doubleValue() + r.getValue());
			entrySize.replace(r.getYear(), entrySize.get(r.getYear()).intValue() + 1);
		}
		
		map.forEach((key, value) -> map.replace(key, value /= entrySize.get(key).intValue() ));
		
		return map;
	}
	
	public Map<Double, Integer> NumberOfRatingByRatingIncrements() {
		
		Map<Double, Integer> map = new HashMap<Double, Integer>();
		
		map.put(0.5, 0);
		map.put(1.0, 0);
		map.put(1.5, 0);
		map.put(2.0, 0);
		map.put(2.5, 0);
		map.put(3.0, 0);
		map.put(3.5, 0);
		map.put(4.0, 0);
		map.put(4.5, 0);
		map.put(5.0, 0);
		
		for(Rating r : rating) {
			if(map.get(r.getValue()) == null) {
				map.put(r.getValue(), 0);
			}
			
			map.replace(r.getValue(), map.get(r.getValue()).intValue() + 1);
		}
		
		return map;
	}
	
	public int NumberOfRating() {
		
		return rating.size();
	}
	
	public void Rate(double r) {
		
		rating.add(new Rating(r, Calendar.getInstance().get(Calendar.YEAR)));
	}
	
}
