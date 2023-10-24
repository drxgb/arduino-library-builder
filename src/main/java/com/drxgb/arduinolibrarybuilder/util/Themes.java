package com.drxgb.arduinolibrarybuilder.util;

import java.net.URL;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;
import com.drxgb.arduinolibrarybuilder.service.FileThemeLoader;
import com.drxgb.arduinolibrarybuilder.service.JarThemeLoader;
import com.drxgb.arduinolibrarybuilder.service.SimpleThemeLoader;
import com.drxgb.arduinolibrarybuilder.service.ThemeLoader;

/**
 * Utilitário responsável por gerar carregadores de temas (<code>ThemeLoader</code>)
 * 
 * @author Dr.XGB
 * @version 1.0.0
 * @see ThemeLoader
 */
public abstract class Themes
{
	/**
	 * Cria uma implementação de <code>ThemeLoader</code> de acordo
	 * com o caminho da pasta
	 * @param path Caminho da pasta dos temas
	 * @return O carregador de tema adequado para o tipo do recurso
	 */
	public static ThemeLoader makeThemeLoader(String path)
	{
		URL resource = ArduinoLibraryBuilder.class.getResource(path);
		String externalForm = resource.toExternalForm();
		
		if (externalForm.startsWith("file:/"))
			return new FileThemeLoader();
		if (externalForm.startsWith("jar:file:/"))
			return new JarThemeLoader();
		return new SimpleThemeLoader();
	}
	
	
	/**
	 * Verifica se o arquivo é um CSS
	 * @param fileName Nome do arquivo a ser testado
	 * @return Se o arquivo é uma folha de estilo
	 */
	public static boolean isStylesheet(String fileName)
	{
		return fileName.endsWith(".css");
	}
	
	
	/**
	 * Recebe o nome do arquivo do estilo
	 * @param fileName Nome do arquivo CSS
	 * @return O nome do estilo
	 */
	public static String getThemeName(String fileName)
	{		
		 return (isStylesheet(fileName))
				? fileName.substring(0, fileName.lastIndexOf('.'))
		 		: fileName;
	}
}
