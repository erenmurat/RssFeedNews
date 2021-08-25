package com.news.controller;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.news.service.UploadRssService;

@RestController
public class NewsController {

	@Autowired
	UploadRssService uploadRssService;

	
	@Transactional
	@RequestMapping(value = "/analyse/new", method = RequestMethod.POST)
	public ResponseEntity<String> analyseRssFeed(@RequestBody List<String> rssURL) {

		UUID uuid = UUID.randomUUID();

		if (rssURL.size() < 2) {
			return new ResponseEntity<>("Minimum Rss feed Url should be 2", HttpStatus.PRECONDITION_FAILED);
		}
		
		if(rssURL.stream().distinct().count()< 2) {
				return new ResponseEntity<>("Minimum Rss feed Url should be 2(must be min 2 unique)", HttpStatus.PRECONDITION_FAILED);
			 
		}

		uploadRssService.uploadRSS(rssURL, uuid.toString());
		return new ResponseEntity<>(uuid.toString(), HttpStatus.OK);

	}

}
