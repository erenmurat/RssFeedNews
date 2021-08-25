package com.news.controller.model;

import java.util.List;

public class DetailedNews {

	private String keyWord;
	private List<News> news;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}
