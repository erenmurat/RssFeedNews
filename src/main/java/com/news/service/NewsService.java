package com.news.service;

import java.util.List;

import com.news.model.News;

public interface NewsService {

	public List<News> getSearchedNews(String keyword);

	public void saveNews(News news);

	public List<Object[]> getMaxKeyWords(String keyword);

	public List<String> getKeywords(String uniqueId, int limit);

	public List<News> getNewsByuniqueIDandKeyword(String uniqueID, String keyword);

}
