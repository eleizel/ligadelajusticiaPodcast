package com.katana.dicecast.model;

import java.io.File;

public class Mp3Entry {
	private String title;
	private File mp3;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File getMp3() {
		return mp3;
	}
	public void setMp3(File mp3) {
		this.mp3 = mp3;
	}
}
