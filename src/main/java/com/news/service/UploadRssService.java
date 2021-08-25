package com.news.service;

import java.util.List;

public interface UploadRssService {

	public void uploadRSS(List<String> rssURL, String uniqueID);
}
