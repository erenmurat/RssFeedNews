package com.news.controller.model;

public class News {

	private String title;
    private String link;
    
    public News(String title, String link) {
    	this.title = title;
    	this.link = link;
    }
    
    public News(com.news.model.News news) {
    	this.setLink(news.getLink());
    	this.setTitle(news.getTitle());
    }
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
    
}
