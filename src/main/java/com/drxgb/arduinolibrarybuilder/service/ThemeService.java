package com.drxgb.arduinolibrarybuilder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.model.Theme;

import javafx.collections.ObservableList;
import javafx.scene.Scene;

/**
 * Responsável por gerenciar e aplicar temas
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class ThemeService
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private List<Theme> themes;
	private int currentIndex;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	public ThemeService()
	{
		themes = new ArrayList<>();
		currentIndex = -1;
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe o tema atual
	 * @return O tema atual
	 */
	public Theme getCurrentTheme()
	{
		try
		{
			return themes.get(currentIndex);
		}
		catch (IndexOutOfBoundsException e)
		{
			return null;
		}
	}
	
	
	/**
	 * Define o tema atual alternando somente o índice da lista
	 * @param index O índice na lista dos temas
	 */
	public void setCurrentTheme(int index)
	{
		if (index >= themes.size())
			index = -1;
		currentIndex = index;
	}
	
	
	/**
	 * Define o tema atual pelo nome do arquivo do estilo.
	 * @param name Nome do arquivo CSS
	 */
	public void setCurrentTheme(String name)
	{
		Optional<Theme> optional = themes.stream()
				.filter(t -> t.getPath().equals(name))
				.findFirst();
		int index = -1;
		
		if (optional.isPresent())
			index = themes.indexOf(optional.get());
		setCurrentTheme(index);
	}
	
	
	/**
	 * Define o tema atual de acordo com um tema dado
	 * @param theme
	 */
	public void setCurrentTheme(Theme theme)
	{
		setCurrentTheme(themes.indexOf(theme));
	}
	
	
	/**
	 * Aplica o tema
	 * @param parent
	 */
	public void applyTheme(Scene scene)
	{
		if (scene != null)
		{
			String path = getCurrentTheme().getPath();
			ObservableList<String> styles = scene.getStylesheets();
			styles.clear();
			styles.add(ArduinoLibraryBuilder.class.getResource(path).toExternalForm());
		}
	}


	/*
	 * ===========================================================
	 * 			*** GETTERS E SETTERS ***
	 * ===========================================================
	 */

	public List<Theme> getThemes()
	{
		return themes;
	}


	public void setThemes(List<Theme> themes)
	{
		this.themes = themes;
	}	
}
