package com.news.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dal.NewsRepository;
import com.news.model.KeyWords;
import com.news.model.News;
import com.news.util.StopWordsClasspathLoaderImpl;
import com.news.util.StopWordsLoader;
import com.news.util.WordSelector;
import com.news.util.WordSelectorImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Service
public class UploadRssServiceImpl implements UploadRssService {

	private final Logger logger = LoggerFactory.getLogger(UploadRssServiceImpl.class);

	@Autowired
	NewsRepository newsRepository;

	@Override
	public void uploadRSS(List<String> rssURL, final String uniqueID) {
		URL url = null;
		XmlReader xmlReader = null;

		StopWordsLoader stopWordsLoader = new StopWordsClasspathLoaderImpl();
		WordSelector wordSelector = new WordSelectorImpl(stopWordsLoader);
		 
		try {
			for (String urlStr : rssURL.stream().distinct().collect(Collectors.toList())) {
				url = new URL(urlStr);
				xmlReader = new XmlReader(url);
				SyndFeed feeder = new SyndFeedInput().build(xmlReader);

				for (Iterator iterator = feeder.getEntries().iterator(); iterator.hasNext();) {
					SyndEntry syndEntry = (SyndEntry) iterator.next();
					News news = new News();
					news.setLink(syndEntry.getLink().toString());
					news.setTitle(syndEntry.getTitle().toString());
					news.setUniqueID(uniqueID);

					List<String> selectedKeyWords = wordSelector.getCleanWord(syndEntry.getTitle());
					List<KeyWords> keywordList = new ArrayList<>();

					for (String wrd : selectedKeyWords) {
						KeyWords kw = new KeyWords();
						kw.setWord(wrd);
						kw.setNews(news);
						keywordList.add(kw);
					}

					news.setKeyWords(keywordList);
					newsRepository.save(news);	
				}
			}
			logger.info("Succesfully saved");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (xmlReader != null)
					xmlReader.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage());
				e2.printStackTrace();
			}
		}

	}

}
