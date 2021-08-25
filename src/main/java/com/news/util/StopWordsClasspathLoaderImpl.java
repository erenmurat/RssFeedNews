package com.news.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopWordsClasspathLoaderImpl implements StopWordsLoader {

	private final Logger logger = LoggerFactory.getLogger(StopWordsClasspathLoaderImpl.class);

	
	private final List<String> stopwords;

	public StopWordsClasspathLoaderImpl() {
		this("/stopwords.txt");
	}

	public StopWordsClasspathLoaderImpl(String stopwordsFilename) {
		List<String> tmpStopwords = null;
		try {
			tmpStopwords = Files.lines(Paths.get(getClass().getResource(stopwordsFilename).toURI()))
					.collect(Collectors.toList());
		} catch (IOException | URISyntaxException | NullPointerException e) {
			System.err
					.println("Unable to load stopwords file 'stopwords.txt' from classpath. Will assume 0 stopwords!");
		} finally {
			this.stopwords = (tmpStopwords != null) ? tmpStopwords : Collections.emptyList();
		}
	}

	@Override
	public boolean containsWord(String word) {
		return stopwords.contains(word);
	}

}
