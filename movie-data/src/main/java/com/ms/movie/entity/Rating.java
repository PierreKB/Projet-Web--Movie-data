package com.ms.movie.entity;

public class Rating {
	
	private double value;
	private int year;
	
	public Rating() {
		setValue(0.0);
		setYear(1970);
	}
	
	public Rating(double value, int year) {
		this.setValue(value);
		this.setYear(year);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
