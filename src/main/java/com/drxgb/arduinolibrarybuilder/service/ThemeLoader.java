package com.drxgb.arduinolibrarybuilder.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.drxgb.arduinolibrarybuilder.model.Theme;
import com.drxgb.util.StringFormatter;

/**
 * Classe utilitária responsável por carregar temas
 */
public abstract class ThemeLoader
{
	/**
	 * Carrega um conjunto de temas através de arquivos fornecidos
	 * por um diretório
	 * @param path Caminho da pasta de folhas de estilo
	 * @return Uma lista de temas
	 */
	public static List<Theme> loadFromFolder(File path)
	{
		List<Theme> themes = new ArrayList<>();
		
		if (path != null && path.isDirectory())
		{
			File[] files = path.listFiles();
			String fileName;
			String extension;
			Theme theme;
			
			for (File file : files)
			{
				String[] chunks = file.getName().split("\\.");
				
				fileName = StringFormatter.capitalize(chunks[0]);
				extension = chunks[1];
				
				if (extension.equals("css"))
				{
					theme = new Theme(
							fileName,
							file.getParentFile().getName() + File.separator + file.getName()
					);
					themes.add(theme);
				}
			}
		}
		
		return themes;
	}
}
