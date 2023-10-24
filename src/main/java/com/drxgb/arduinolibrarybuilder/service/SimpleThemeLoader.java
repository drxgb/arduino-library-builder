package com.drxgb.arduinolibrarybuilder.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.drxgb.arduinolibrarybuilder.model.Theme;

/**
 * Cria uma lista de temas com arquivos fixos
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class SimpleThemeLoader implements ThemeLoader
{

	/**
	 * @see com.drxgb.arduinolibrarybuilder.service.ThemeLoader#load(java.lang.String)
	 */
	@Override
	public List<Theme> load(String path)
	{
		return Arrays.asList(
				new Theme("Dark", "style" + File.separator + "dark.css"),
				new Theme("Light", "style" + File.separator + "light.css")
		);
	}

}
