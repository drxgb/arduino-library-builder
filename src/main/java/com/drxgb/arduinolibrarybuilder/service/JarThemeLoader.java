package com.drxgb.arduinolibrarybuilder.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.drxgb.arduinolibrarybuilder.model.Theme;
import com.drxgb.arduinolibrarybuilder.util.Themes;
import com.drxgb.util.StringFormatter;

/**
 * Responsável por carregar temas dentro de um JAR
 */
public class JarThemeLoader implements ThemeLoader
{
	/**
	 * @see com.drxgb.arduinolibrarybuilder.service.ThemeLoader#load(java.io.File)
	 */
	@Override
	public List<Theme> load(String path)
	{
		URL resource = getClass().getProtectionDomain().getCodeSource().getLocation();
		
		try (ZipInputStream zip = new ZipInputStream(resource.openStream()))
		{
			List<Theme> themes = new ArrayList<>();
			ZipEntry entry;
			
			while ((entry = zip.getNextEntry()) != null)
			{
				String entryName = entry.getName();
				
				if (entryName.startsWith("com/drxgb/arduinolibrarybuilder/style/"))
				{
					int index = entryName.lastIndexOf('/');
					String name = entryName.substring(index + 1).trim();
					
					if (!name.isEmpty())
					{
						if (Themes.isStylesheet(name))
						{
							String parent; 
							String themeName = StringFormatter.capitalize(Themes.getThemeName(name));
							Theme theme;
							
							index = entryName.substring(0, index).lastIndexOf('/');
							parent = entryName.substring(index + 1);
							theme = new Theme(themeName, parent);
							themes.add(theme);
						}
					}
				}
			}
			
			return themes;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
}
