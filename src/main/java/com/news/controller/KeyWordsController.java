package com.news.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.news.controller.model.DetailedNews;
import com.news.controller.model.News;
import com.news.service.NewsService;

@RestController
public class KeyWordsController {

	private final Logger logger = LoggerFactory.getLogger(KeyWordsController.class);

	private final int LIMIT = 3;
	@Autowired
	NewsService newsService;

	@RequestMapping(value = "/frequency/{execution}", method = RequestMethod.GET)
	public ResponseEntity<List<DetailedNews>> getNewsByExecutionId(@PathVariable String execution) {

		List<News> newsList = new ArrayList<>();
		List<DetailedNews> detailedNews = new ArrayList<DetailedNews>();
		List<String> keyWordsList = new ArrayList<String>();

		try {
			keyWordsList = newsService.getKeywords(execution, LIMIT);

			for (String keyword : keyWordsList) {
				System.out.println("KeyWords" + keyword);
				DetailedNews dn = new DetailedNews();
				dn.setKeyWord(keyword);
				newsList = newsService.getNewsByuniqueIDandKeyword(execution, keyword).stream()
						.map(p -> new News(p.getTitle(), p.getLink())).toList();
				dn.setNews(newsList);
				detailedNews.add(dn);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		if (detailedNews.size() == 0) {
			return new ResponseEntity<List<DetailedNews>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<DetailedNews>>(detailedNews, HttpStatus.OK);
	}
}
