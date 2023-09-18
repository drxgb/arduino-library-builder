package com.drxgb.arduinolibrarybuilder.singleton;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.drxgb.arduinolibrarybuilder.util.FileSystemIcon;

import javafx.scene.image.Image;

/**
 * Armazena e trata os ícones de arquivos para reutilização,
 * evitando desempenho desnecessário ao gerar novos ícones
 * a cada solicitação feita pelo programa cliente.
 * 
 * @author Dr.XGB
 * @version 1.0.0
 */
public final class FileIconManager
{
	/*
	 * ===========================================================
	 * 			*** ATRIBUTOS ***
	 * ===========================================================
	 */
	
	private static FileIconManager instance;
	private static Map<String, Image> icons;
	
	
	/*
	 * ===========================================================
	 * 			*** CONSTRUTORES ***
	 * ===========================================================
	 */
	
	private FileIconManager()
	{
		icons = new HashMap<>();
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe ou cria a imagem que representa o ícone do arquivo
	 * @param file
	 * @return
	 */
	public Image getImage(File file)
	{
		String fileName = file.getName();
		String key;
		Image image;
		
		if (file.isDirectory())
			key = "folder";
		else if (fileName.contains("."))
			key = fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return null;
		
		if (!icons.containsKey(key))
		{
			image = FileSystemIcon.makeImage(file);
			icons.put(key, image);
		}
		
		return icons.get(key);
	}
	
	
	/*
	 * ===========================================================
	 * 			*** MÉTODOS PÚBLICOS ESTÁTICOS ***
	 * ===========================================================
	 */
	
	/**
	 * Recebe a instância única do gerenciador de ícones
	 * @return A instância do gerenciador
	 */
	public static FileIconManager getInstance()
	{
		if (instance == null)
			instance = new FileIconManager();
		return instance;
	}
}
