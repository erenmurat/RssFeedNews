package com.news.business;

 import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.news.util.StopWordsClasspathLoaderImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StopWordsClasspathLoaderImplTest {

	  static List<String> expectedStopWords;

	    @BeforeAll
	    static void setupClass() throws IOException, URISyntaxException {
	        expectedStopWords = Files
	                .lines(Paths.get(StopWordsClasspathLoaderImplTest.class.getResource("/stopwords.txt").toURI()))
	                .collect(Collectors.toList());
	    }

	    @Test
	    void GIVEN_aStopwordsFile_WHEN_loadStopWords_THEN_returnTrueForEveryExpectedStopWord() {
	        StopWordsClasspathLoaderImpl stopWordsClasspathLoader = new StopWordsClasspathLoaderImpl();
	        for (String expectedStopWord : expectedStopWords) {
	            assertTrue(
	                    stopWordsClasspathLoader.containsWord(expectedStopWord),
	                    String.format("Word '%s' is missing in Stopwords!", expectedStopWord)
	            );
	        }
	    }

	    @Test
	    void GIVEN_noStopwordsFile_WHEN_loadStopWords_THEN_returnFalseForEveryContains() {
	        StopWordsClasspathLoaderImpl stopWordsClasspathLoader = new StopWordsClasspathLoaderImpl("/non-existing-file.txt");
	        Assertions.assertFalse(stopWordsClasspathLoader.containsWord(""));
	    }

}
