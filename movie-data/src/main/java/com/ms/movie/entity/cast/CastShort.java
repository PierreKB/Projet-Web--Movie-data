package com.ms.movie.entity.cast;

import java.util.List;

public class CastShort {
	
	private String job;
    private List<CastShortItem> items;
    
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public List<CastShortItem> getItems() {
		return items;
	}
	public void setItems(List<CastShortItem> items) {
		this.items = items;
	}
}
