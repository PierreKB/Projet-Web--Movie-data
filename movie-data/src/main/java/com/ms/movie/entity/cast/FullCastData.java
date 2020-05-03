package com.ms.movie.entity.cast;

import java.util.List;

public class FullCastData {
	
	private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private String year;
    
    private CastShort directors;
    private CastShort writers;
    private List<ActorShort> actors;
    private List<CastShort> others;
    private String errorMessage;
    
    
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFullTitle() {
		return fullTitle;
	}
	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public CastShort getDirectors() {
		return directors;
	}
	public void setDirectors(CastShort directors) {
		this.directors = directors;
	}
	public CastShort getWriters() {
		return writers;
	}
	public void setWriters(CastShort writers) {
		this.writers = writers;
	}
	public List<ActorShort> getActors() {
		return actors;
	}
	public void setActors(List<ActorShort> actors) {
		this.actors = actors;
	}
	public List<CastShort> getOthers() {
		return others;
	}
	public void setOthers(List<CastShort> others) {
		this.others = others;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getImDbId() {
		return imDbId;
	}
	public void setImDbId(String imDbId) {
		this.imDbId = imDbId;
	}
}
