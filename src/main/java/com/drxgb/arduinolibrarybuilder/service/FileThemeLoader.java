package com.drxgb.arduinolibrarybuilder.service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.model.Theme;
import com.drxgb.arduinolibrarybuilder.util.Themes;
import com.drxgb.util.StringFormatter;

/**
 * Responsável por carregar temas em arquivos no disco
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public class FileThemeLoader implements ThemeLoader
{
	/**
	 * @see com.drxgb.arduinolibrarybuilder.service.ThemeLoader#load(java.io.File)
	 */
	@Override
	public List<Theme> load(String path)
	{		
		List<Theme> themes = new ArrayList<>();
		URL resource = ArduinoLibraryBuilder.class.getResource(path);
		File dir = new File(resource.getPath());
		
		if (dir != null && dir.isDirectory())
		{
			File[] files = dir.listFiles();			
			for (File file : files)
			{
				String fileName = file.getName();				
				if (Themes.isStylesheet(fileName))
				{
					String themeName = StringFormatter.capitalize(Themes.getThemeName(fileName));
					StringBuilder sb = new StringBuilder();
					Theme theme;

					sb.append(file.getParentFile().getName())
						.append(File.separator)
						.append(file.getName());
					
					theme = new Theme(themeName, sb.toString());
					themes.add(theme);
				}
			}
		}
		
		return themes;
	}
}
