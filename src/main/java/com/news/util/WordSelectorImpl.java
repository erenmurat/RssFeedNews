package com.news.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
 
public class WordSelectorImpl implements WordSelector {

	private final Logger logger = LoggerFactory.getLogger(WordSelectorImpl.class);

	private final StopWordsLoader stopWordsLoader;
	private final Pattern isValidWordPattern;

	public WordSelectorImpl(StopWordsLoader stopWordsLoader) {
		this.stopWordsLoader = stopWordsLoader;
		this.isValidWordPattern = Pattern.compile("[A-Za-z]+");
	}

	@Override
	public List<String> getCleanWord(String text) {
		Objects.requireNonNull(text, "Text input must not be null");

		List<String> allWords = Arrays.stream(text.trim().split("\\s|-{2,}"))
				.map(this::removeDotOnWordEnd)
				.map(this::removeHypens)
				.map(this::removeQuote)
				.map(this::removeQuoteLeft)
				.map(this::removeQuoteRight)
				.filter(this::isWordValid)
				.filter(this::filterStopWords)
				.distinct()
				.collect(Collectors.toList());
		
		return allWords;
	}

	private String removeDotOnWordEnd(String w) {
		return (!w.trim().isEmpty() && w.charAt(w.length() - 1) == '.') ? w.substring(0, w.length() - 1) : w;
	}
	
 	private String removeHypens(String w) {
		return w.replaceAll("-", "");
	}

	private String removeQuoteLeft(String w) {
		return w.replaceAll("’", "");
	}
	
	private String removeQuoteRight(String w) {
		return w.replaceAll("‘", "");
	}
	

	private String removeQuote(String w) {
		return w.replaceAll("'", "");
	}
	
	private boolean isWordValid(String word) {
		return isValidWordPattern.matcher(word).matches();
	}

	private boolean filterStopWords(String w) {
		return !stopWordsLoader.containsWord(w.trim().toLowerCase());
	}

}
