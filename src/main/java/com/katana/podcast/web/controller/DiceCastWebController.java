package com.katana.podcast.web.controller;


//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.katana.podcast.model.*;
//import com.katana.podcast.repository.*;
import com.katana.podcast.utils.*;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;

@Controller
public class DiceCastWebController {
	
	private static final Menu menu = new Menu();
	
	private SpreakerRSSFeed feed = new SpreakerRSSFeed();
	
//	@Autowired
//	SimpleFeedRepository repository;
//	
//	@Autowired
//	Mp3Repository repositoryMp3;

    @SuppressWarnings("unchecked")
	@RequestMapping("/home") 
    public String serveHome (Model model) {
    	SyndFeed myFeed = feed.getFeed();
    	SimpleRSSEntry entry = null;
    	List<SyndEntryImpl> entryList = myFeed.getEntries();
    	if (!entryList.isEmpty()) {
    		entry = new SimpleRSSEntry(entryList.get(0));
    	}
    	model.addAttribute("lastEntry",entry);
    	model.addAttribute("menu", menu);
    	
    	return "home";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/podcast")
    public String printPodcastFeed (Model model) {
    	SyndFeed myFeed = feed.getFeed();
    	List<SyndEntryImpl> entryList = myFeed.getEntries();
    	List<SimpleRSSEntry> simpleEntryList = new ArrayList<SimpleRSSEntry>();
    	for (SyndEntryImpl entry : entryList) {
    		SimpleRSSEntry simpleEntry = new SimpleRSSEntry();
    		if (entry!=null && entry.getTitle()!=null) {
    			simpleEntry.setTitle(entry.getTitle());
    		} else {
    			simpleEntry.setTitle("No hay título");
    		}
    		if (entry!=null && entry.getDescription()!=null && entry.getDescription().getValue()!=null) {
    			simpleEntry.setDescription(entry.getDescription().getValue());
	    	} else {
				simpleEntry.setDescription("No hay descripción");
			}
    		if (entry!=null && entry.getLink()!=null) {
    			simpleEntry.setLink(entry.getLink());
    		} else {
    			simpleEntry.setLink("No hay enlace");
    		}
    		List<SyndEnclosure> mp3List = entry.getEnclosures();
    		for (SyndEnclosure mp3 : mp3List) {
    			if (mp3!=null && mp3.getUrl()!=null) {
    				simpleEntry.setMp3(mp3.getUrl());
    				break;
    			}
    		}
    		
    		simpleEntryList.add(simpleEntry);
    		
//    		if (repository.findByTitle(simpleEntry.getTitle())!=null && repository.findByTitle(simpleEntry.getTitle()).getTitle()!=null) {
//    			System.out.println(simpleEntry.getTitle()+" ya existe en la BBDD");
//    		} else {
//    			repository.save(simpleEntry);
//    		}
//    		
//    		int index = 0; 
//    		if (simpleEntry.getTitle().indexOf(":")!=-1) {
//    			index = simpleEntry.getTitle().indexOf(":");
//    		} else {
//    			index =simpleEntry.getTitle().length();
//    		}
//    			
//    		String fileName = simpleEntry.getTitle().substring(0, index);
//    		boolean fileExists = false;
//    		try {
//				File myFile = new File("C:\\dev\\tmp\\"+fileName+".mp3");
//				if (myFile.exists() && !myFile.isDirectory()){
//					fileExists = true;
//				}
//    		} catch (Exception e) {
//    			System.out.println("No existe el fichero "+fileName+".mp3");
//    		}
//    		if (fileExists) {
//    			System.out.println(simpleEntry.getTitle()+" ya existe en la BBDD de Mp3");
//    		} else {
//				try {
//					URLConnection conn = new URL(simpleEntry.getMp3()).openConnection();
//					InputStream is = conn.getInputStream();
//					File myFile = new File("C:\\dev\\tmp\\"+fileName+".mp3");
//					OutputStream outstream = new FileOutputStream(myFile);
//				    byte[] buffer = new byte[4096];
//				    int len;
//				    while ((len = is.read(buffer)) > 0) {
//				        outstream.write(buffer, 0, len);
//				    }
//				    outstream.close();
//				    
//				    Mp3Entry mp3Entry = new Mp3Entry();
//				    mp3Entry.setMp3(myFile);
//				    mp3Entry.setTitle(simpleEntry.getTitle());
//				    repositoryMp3.save(mp3Entry);
//				} catch (Exception e) {
//					System.out.println("Fail");
//				}
//    		}
    	}
    	simpleEntryList.removeAll(Collections.singleton(null));
    	model.addAttribute("menu", menu);
    	model.addAttribute("title", myFeed.getTitle());
    	model.addAttribute("description",myFeed.getDescription());
    	model.addAttribute("image", myFeed.getImage().getUrl());
    	model.addAttribute("entryList", simpleEntryList);
    	return "podcast";
    }
    
	@RequestMapping("/podcastEntry")
    public String printPodcastEntry (Model model, 
    		@RequestParam(value="title", required=false, defaultValue="TÃ­tulo") String title,
    		@RequestParam(value="description", required=false, defaultValue="DescripciÃ³n") String description,
    		@RequestParam(value="link", required=false, defaultValue="Enlace") String link,
    		@RequestParam(value="mp3", required=false, defaultValue="Mp3") String mp3) {
//    	SimpleRSSEntry simpleEntry = repository.findByTitle(title);
		SimpleRSSEntry simpleEntry = new SimpleRSSEntry();
		simpleEntry.setTitle(title);
		simpleEntry.setDescription(description);
		simpleEntry.setLink(link);
		simpleEntry.setMp3(mp3);
    	model.addAttribute("menu", menu);
    	model.addAttribute("simpleEntry", simpleEntry);
    	return "podcastEntry";
    }
	
	@RequestMapping("/contactos")
	public String contactos(Model model) {
		model.addAttribute("menu", menu);
		return "contactos";
	}
	
}