package com.katana.podcast.model;

import java.util.List;
import org.springframework.data.annotation.Id;

public class SimpleFeed {
	@Id
	public String id;
	
	public String title;
	public String description;
	public String image;
	public List<SimpleRSSEntry> entryList;
	
	public SimpleFeed() {}
	
	public SimpleFeed (String title, String description, String image, List<SimpleRSSEntry> entryList) {
		this.title = title;
		this.description = description;
		this.image = image;
		this.entryList = entryList;
	}
	
	@Override
	public String toString() {
		return String.format("SimpleFeed[title=%s, description=%s, image=%s, entryList=%s]", title, description, image, entryList.toString());
	}
	
}
