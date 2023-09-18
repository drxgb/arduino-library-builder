package com.drxgb.arduinolibrarybuilder.util;

import java.io.File;
import java.io.InputStream;

import com.drxgb.arduinolibrarybuilder.ArduinoLibraryBuilder;

import javafx.scene.image.Image;

/**
 * Procura pelo ícone do arquivo no sistema operacional
 * @author Dr.XGB
 * @version 1.0.0
 */
public abstract class FileSystemIcon
{	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Cria a imagem para ser usada como ícone
	 * @param file O arquivo da imagem
	 * @return A imagem do arquivo
	 */
	public static Image makeImage(File file)
	{
		Image image = null;
		
		if (file.isDirectory())
		{
			String path = ArduinoLibraryBuilder.ICON_PATH + "folder.png";
			InputStream input = ArduinoLibraryBuilder.class.getResourceAsStream(path);
			image = new Image(input);
		}
		else
		{
			String fileName = file.getName();
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			image = getImageByExtension(extension);
		}
		
		return image;
	}
	
	
	/**
	 * Recebe a imagem de acordo com a extensão dada
	 * @param extension a extensão do arquivo
	 * @return A imagem que representa a extensão do arquivo
	 */
	public static Image getImageByExtension(String extension)
	{
		String path;
		InputStream input;
		StringBuilder sb = new StringBuilder();
		
		sb.append(ArduinoLibraryBuilder.ICON_PATH)
			.append("ext/")
			.append(extension)
			.append(".png");
		
		path = sb.toString();
		input = ArduinoLibraryBuilder.class.getResourceAsStream(path);
		
		if (input == null)
		{
			path = ArduinoLibraryBuilder.ICON_PATH + "file.png";
			input = ArduinoLibraryBuilder.class.getResourceAsStream(path);
		}
		
		return new Image(input);
	}
}
