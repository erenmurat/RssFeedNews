package com.news.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "NEWS")
public class News implements Serializable {

	private static final long serialVersionUID = -1243338434092573562L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long newsid;

	
	@Column
	private String title;

	@Column(length = 1024)
	private String link;

	@Column
	private String uniqueID;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonBackReference
	//@JoinColumn(name = "KEYWORD_ID", nullable = false)
	private List<KeyWords> keyWords;

	public List<KeyWords> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<KeyWords> keywords) {
		this.keyWords = keywords;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getNewsid() {
		return newsid;
	}

	public void setNewsid(Long newsid) {
		this.newsid = newsid;
	}

	@Override
	public String toString() {
		return "News{" + "id=" + newsid + ", title='" + title + '\'' + ", link=" + link + '}';
	}

}
