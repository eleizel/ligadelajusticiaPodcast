package com.katana.podcast.model;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class SimpleRSSEntry {
	String title;
	String description;
	String link;
	String mp3;
	
	public SimpleRSSEntry () {
	}
	
	@SuppressWarnings("unchecked")
	public SimpleRSSEntry (SyndEntryImpl entry) {
		if (entry!=null && entry.getTitle()!=null) {
			this.setTitle(entry.getTitle());
		} else {
			this.setTitle("No hay título");
		}
		if (entry!=null && entry.getDescription()!=null && entry.getDescription().getValue()!=null) {
			this.setDescription(entry.getDescription().getValue());
    	} else {
    		this.setDescription("No hay descripción");
		}
		if (entry!=null && entry.getLink()!=null) {
			this.setLink(entry.getLink());
		} else {
			this.setLink("No hay enlace");
		}
		List<SyndEnclosure> mp3List = entry.getEnclosures();
		for (SyndEnclosure mp3 : mp3List) {
			if (mp3!=null && mp3.getUrl()!=null) {
				this.setMp3(mp3.getUrl());
				break;
			}
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getMp3() {
		return mp3;
	}
	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}
	@Override
	public String toString() {
		return "[SimpleRSSEntry] = {"+title+" | "+description+" | "+link+"}";
	}
	
}
