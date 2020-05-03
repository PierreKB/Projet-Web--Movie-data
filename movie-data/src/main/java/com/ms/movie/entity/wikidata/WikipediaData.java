package com.ms.movie.entity.wikidata;

public class WikipediaData {
	
	private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private String year;
    
	private String language;
	private String titleInLanguage;
	private String url;
	
	private WikipediaDataPlot plotShort;
	private WikipediaDataPlot plotFull;
	
	private String errorMessage;
	
	public String getImdbid() {
		return imDbId;
	}
	public void setImdbid(String imdbid) {
		this.imDbId = imdbid;
	}
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTitleInLanguage() {
		return titleInLanguage;
	}
	public void setTitleInLanguage(String titleInLanguage) {
		this.titleInLanguage = titleInLanguage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public WikipediaDataPlot getPlotShort() {
		return plotShort;
	}
	public void setPlotShort(WikipediaDataPlot plotShort) {
		this.plotShort = plotShort;
	}
	public WikipediaDataPlot getPlotFull() {
		return plotFull;
	}
	public void setPlotFull(WikipediaDataPlot plotFull) {
		this.plotFull = plotFull;
	}
}
