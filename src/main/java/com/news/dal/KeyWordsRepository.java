package com.news.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.model.KeyWords;

@Repository
public interface KeyWordsRepository extends JpaRepository<KeyWords , Long>{ 

	@Query(value = "SELECT t.WORD from (SELECT count(word) as total, word FROM KEYWORDS k, NEWS n where k.NEWS_NEWSID = n.NEWSID and n.uniqueid=?1 group by word order by total desc fetch first ?2 rows only) t" , nativeQuery = true)
	public List<String> getKeywords(String uniqueId, int limit);
	 
}
