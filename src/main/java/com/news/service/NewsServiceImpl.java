package com.news.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dal.KeyWordsRepository;
import com.news.dal.NewsRepository;
import com.news.model.News;

@Service
public class NewsServiceImpl implements NewsService {

	private final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired 
	NewsRepository newsRepo;
	
	@Autowired 
	KeyWordsRepository kwRepo;
	
	@Override
	public List<News> getSearchedNews(String uniqueId) {
		List<News> newsList =
				(List<News>) newsRepo.getNewsByuniqueID(uniqueId).stream()
				.collect(Collectors.toList());
		logger.info("News Size:" +newsList.size());
		return newsList;
	}

	@Override
	public void saveNews(News news) {
		 newsRepo.save(news);
	}

	@Override
	public List<Object[]> getMaxKeyWords(String keyword) {
		return  newsRepo.getMaxKeyWords(keyword);
	}

	@Override
	public List<String> getKeywords(String uniqueId, int limit) {
		return kwRepo.getKeywords(uniqueId, limit);
	}

	@Override
	public List<News> getNewsByuniqueIDandKeyword(String uniqueID, String keyword) {
		return newsRepo.getNewsByuniqueIDandKeyword(uniqueID, keyword);	
	}
}
