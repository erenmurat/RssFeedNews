package com.news.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.news.util.StopWordsClasspathLoaderImpl;
import com.news.util.StopWordsLoader;
import com.news.util.WordSelector;
import com.news.util.WordSelectorImpl;

public class WordSelectorImplTest {

	static Stream<Arguments> produceTestData() {
		return Stream.of(arguments("Mary had a little lamb", 4L), arguments("", 0L ),
				arguments("Foo44bar", 0L), 
				arguments("Mary and Tom, her little brother, had a little lamb.", 6L),
				arguments("Mary   had  a     little lamb",4L) );
	}

 	@BeforeEach
	void setup() {
		new TestStopWordLoader();
	}

	@ParameterizedTest
	@MethodSource("produceTestData")
	void GIVEN_aText_WHEN_countWords_THEN_returnNumberOfWords(String text, long expected) {
		StopWordsLoader stopWordsLoader = new StopWordsClasspathLoaderImpl();
		WordSelector wordSelector = new WordSelectorImpl(stopWordsLoader);

		assertEquals(expected, wordSelector.getCleanWord(text).size(), "Keyword is wrong");
	}
 
	static class TestStopWordLoader implements StopWordsLoader {
		@Override
		public boolean containsWord(String word) {
			return false;
		}
	}

}
