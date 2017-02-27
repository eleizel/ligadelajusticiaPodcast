package com.katana.podcast.model;

public class Menu {
	MenuOption[] options;
	
	public Menu() {
		MenuOption podcast = new MenuOption("Podcast", "/podcast");
		MenuOption home = new MenuOption("Home", "/home");
		MenuOption contacto = new MenuOption("Contáctanos", "/contactos");
		MenuOption[] options = {home,podcast,contacto};
		this.setOptions(options);
	}

	public MenuOption[] getOptions() {
		return options;
	}

	public void setOptions(MenuOption[] options) {
		this.options = options;
	}
	
	
}
