package com.katana.podcast.utils;

import java.io.IOException;
import java.net.URL;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class SpreakerRSSFeed {
	
	private static final String rssUrl = "http://www.spreaker.com/show/1987727/episodes/feed";

	public SyndFeed getFeed() {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(new URL(rssUrl)));
		} catch (IllegalArgumentException | FeedException | IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: "+e.getMessage());
		}
		return feed;
	}
}
