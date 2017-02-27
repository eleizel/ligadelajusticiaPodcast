package com.katana.podcast.model;

public class MenuOption {
	private String displayName;
	private String url;
	
	public MenuOption(String displayName, String url) {
		this.setDisplayName(displayName);
		this.setUrl(url);
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
