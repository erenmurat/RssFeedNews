package com.news.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News , Long>{
	
	@Query(value = "SELECT * FROM News WHERE uniqueID = ?1", nativeQuery = true)
	public List<News> getNewsByuniqueID(String uniqueID);
	
	@Query(value = "SELECT k.word, n.title,n.link FROM keywords k, news n WHERE uniqueid = ?1 and k.NEWS_NEWSID = n.NEWSID and k.word in (SELECT t.WORD FROM (SELECT count(word) as total, word FROM KEYWORDS GROUP BY word ORDER BY total desc fetch first 3 rows only) t) ORDER BY 1", nativeQuery =  true)
	public List<Object[]> getMaxKeyWords(String keyword);
	
	
	@Query(value = "select n.NEWSID, n.title, n.link, n.uniqueid from NEWS n, KEYWORDS k Where n.uniqueid=?1 and n.NEWSID = k.NEWS_NEWSID and k.word =?2", nativeQuery = true)
	public List<News> getNewsByuniqueIDandKeyword(String uniqueID, String keyword);
	
 }
