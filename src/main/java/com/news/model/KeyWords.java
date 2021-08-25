package com.news.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KEYWORDS")
public class KeyWords implements Serializable {

	private static final long serialVersionUID = 1794054726443856431L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kwid;

	@Column
	private String word;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private News news;

 	public Long getKwid() {
		return kwid;
	}

	public void setKwid(Long kwid) {
		this.kwid = kwid;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
